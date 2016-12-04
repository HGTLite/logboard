package com.hgt;

import com.google.common.collect.Lists;
import com.hgt.converter.MapJsonConverter;
import com.hgt.es.common.ESAdminOperations;
import com.hgt.es.config.ESConfig;
import com.hgt.filter.LogKeyChecker;
import com.hgt.filter.LogOptionsChecker;
import com.hgt.hbase.common.HBaseOperations;
import com.hgt.msg.HttpUtil;
import com.hgt.obj.CloneUtils;
import com.hgt.tools.LogIdBuilder;
import com.hgt.tools.StatsIdBuilder;
import com.hgt.utils.DateHelper;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.*;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import scala.Tuple2;

import java.io.IOException;
import java.util.*;

/**
 * 日志实时处理类
 */
public class StreamingApp {

    private static final String HOST_IP = "192.168.99.75";

    private static final String STATS_HOST_ENDPOINT = "http://" + HOST_IP + ":8702";

    private static final String MSG_ROUTER_HOST_ENDPOINT = "http://" + HOST_IP + ":8703";

    public static void main(String[] args) {

        //region 读取配置文件
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取kafka配置
        String zkQuorum = prop.getProperty("kafka.zookeeper");
        String group = prop.getProperty("group.name");
        String topics = prop.getProperty("topic.list");
        //读取es配置
        String esCName = prop.getProperty("es.cname");
        String esCHost = prop.getProperty("es.chost");
        String esIndex = prop.getProperty("es.index");
        String esType = prop.getProperty("es.type");
        //endregion

        String sparkAppName = "LogStreaming";
        String sparkMaster = "local[3 ]";
        Duration jobInterval = new Duration(5000);

        //每个话题的分片数
        int numThreads = 2;
        SparkConf sparkConf = new SparkConf().setAppName(sparkAppName).setMaster(sparkMaster);
        JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, jobInterval);

        //存放话题跟分片的映射关系
        Map<String, Integer> topicmap = new HashMap<>();
        String[] topicsArr = topics.split(",");
        int n = topicsArr.length;
        for (int i = 0; i < n; i++) {
            topicmap.put(topicsArr[i], numThreads);
        }

        //从Kafka中获取数据转换成RDD
        JavaPairReceiverInputDStream<String, String> lines = KafkaUtils.createStream(jssc, zkQuorum, group, topicmap);

        //从topic中获取所需数据
        JavaDStream<String> logItems = lines.flatMap(new FlatMapFunction<Tuple2<String, String>, String>() {

            @Override
            public Iterable<String> call(Tuple2<String, String> arg0)
                    throws Exception {

                String s = arg0._2;

                //region 日志处理量计数
                System.out.println("=====原始日志是：" + s);
                Map<String, Integer> params = new HashMap<>();
                params.put("message", 1);
                String targetServerURL = MSG_ROUTER_HOST_ENDPOINT + "/msg/router";
                String str = HttpUtil.post(targetServerURL, params, 3000, 3000, "UTF-8");
                System.out.println("已处理1条日志：" + str);
                //endregion

                HashMap<String, String> logMap = new LinkedHashMap<>();
                logMap.put("logLevel", s.substring(1, 6).trim());
                logMap.put("logTime", s.substring(7, 30).trim());
                logMap.put("codeClass", s.substring(31, 71).trim());
                logMap.put("codeFile", s.substring(72, 92).trim());
                logMap.put("lineNumber", s.substring(93, 96).trim());

                //TODO:!!!!!BUG 日志内容不能含有逗号等
                String logsRight = s.substring(100, s.length() - 2);
                String[] manualLogs = logsRight.split(",");
                int ml = manualLogs.length;

                String[][] m = new String[ml][2];
                //将3个固定的appCode, logType, logMsg加入map
                for (int i = 0; i < 3; i++) {
                    m[i] = manualLogs[i].split(":");
                    String k = m[i][0].replace("\"", "");
                    String v = m[i][1].replace("\"", "");
                    logMap.put(k, v);
                }

                //生成该条日志ID
                String thisLogId = LogIdBuilder.build(logMap.get("appCode"), logMap.get("logType"), logMap.get("logTime"));

                //region 将日志索引进es集群
                HashMap<String, String> esLogMap = new LinkedHashMap<>();
                esLogMap = CloneUtils.clone(logMap);
                String thisTime = esLogMap.get("logTime");
                String formattedTime = thisTime.replace(" ", "T").replace(",", ".") + "Z";
                esLogMap.put("logTime", formattedTime);
                //将剩余部分strLogOptions加入esLogMap
                String strLogOptions = "";
                if (manualLogs[3].substring(13).contains(":")) {
                    for (int k = 3; k < ml; k++) {
                        strLogOptions = strLogOptions + manualLogs[k] + ",";
                    }
                } else {
                    strLogOptions = manualLogs[3];
                }

                strLogOptions = strLogOptions.substring(0, strLogOptions.length() - 1);
                String ko = strLogOptions.split(":")[0].replace("\"", "");
                String vo = strLogOptions.substring(strLogOptions.split(":")[0].length() + 1).replace("\"", "");
                esLogMap.put(ko, vo);

                //region ES配置
                ESConfig esConfig = new ESConfig(esCName, esCHost);
                ESAdminOperations esAdminOperations = new ESAdminOperations(esConfig);
                esAdminOperations.indexingDataByMap(esIndex, esType, thisLogId, esLogMap);
                esAdminOperations.close();
                //endregion
                //endregion

                //将不固定的logOptions加入map
                if (manualLogs[3].substring(13).contains(":")) {
                    for (int j = 3; j < ml; j++) {
                        m[j] = manualLogs[j].split(":");
                        if (j == 3) {
                            //去掉第4个键值对的"logOptions:"
                            String k1 = m[j][1].substring(1).replace("\"", "");
                            String v1 = m[j][2].replace("\"", "");
                            logMap.put(k1, v1);
                        } else if (j == ml - 1) {
                            //去掉最后一个键值对后面的"}"
                            String k1 = m[j][0].replace("\"", "");
                            String v1 = m[j][1].substring(0, m[j][1].length() - 1).replace("\"", "");
                            logMap.put(k1, v1);
                        } else {
                            //普通键值对直接加入map
                            String k1 = m[j][0].replace("\"", "");
                            String v1 = m[j][1].replace("\"", "");
                            logMap.put(k1, v1);
                        }
                    }
                }

                //过滤日志
                if (new LogKeyChecker(logMap).isLogValid() && new LogOptionsChecker(logMap).isLogValid()) {
                    List<String> keys = new ArrayList<String>(logMap.keySet());
                    List<String> vals = new ArrayList<String>(logMap.values());
                    String tableName = "one-log";
                    String cfName = "loginfo";
                    HBaseOperations hBaseOperations = new HBaseOperations();
                    //String rk = RowkeyFactory.build16(logMap.get("logType"), logMap.get("logTime"));
                    //TO-DO：分成2个列族存储
                    hBaseOperations.insertRow(tableName, thisLogId, cfName, keys, vals);
                } else {
                }
                String logMapStr = MapJsonConverter.simpleMapToJsonStr(logMap);
                String addedIdLog = "{" +
                        "\"logId\":\"" + thisLogId + "\"," +
                        logMapStr.substring(1);
                System.out.println("=====扁平化后的日志是：" + addedIdLog);
                return Lists.newArrayList(addedIdLog);
            }
        });

        //region 过滤次要日志，存储有效日志
        JavaDStream<String> validLogs = logItems.filter(new Function<String, Boolean>() {
            @Override
            public Boolean call(String s) throws Exception {
                //System.out.println("=====过滤之后的日志是：" + s);
                return true;
            }
        });
        //endregion

        //region 按应用统计该时段日志
        JavaPairDStream<String, Integer> logCountsByApp = validLogs.mapToPair(
                //JavaPairDStream<String, Integer> logCountsByApp = logItems.mapToPair(
                new PairFunction<String, String, Integer>() {
                    @Override
                    public Tuple2<String, Integer> call(String s) {
                        //System.out.println("=====没有mapToPair的日志是 " + s);
                        String[] flatLogs = s.split(",");
                        String appCode = flatLogs[7].split(":")[1].replace("\"", "");
                        System.out.println("=====统计的app是 " + appCode);
                        return new Tuple2<>(appCode, 1);
                    }
                })
                .reduceByKeyAndWindow(new Function2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer i1, Integer i2) {
                        System.out.println("累加处理 " + i1);
                        return i1 + i2;
                    }
                }, new Duration(5000), new Duration(5000));

        //按应用统计结果存入mysql
        logCountsByApp.foreach(new Function2<JavaPairRDD<String, Integer>, Time, Void>() {
            @Override
            public Void call(JavaPairRDD<String, Integer> values, Time time)
                    throws Exception {
                values.foreach(new VoidFunction<Tuple2<String, Integer>>() {
                    @Override
                    public void call(Tuple2<String, Integer> tuple) throws Exception {
//                        System.out.println("计数之后的结果是" + "Tuple1: " + tuple._1() + ", Tuple2: " + tuple._2());
                        String appCode = tuple._1();
                        String counts = tuple._2().toString();
                        String datetime = DateHelper.getFullStandardDate();
                        String rid = StatsIdBuilder.build(appCode, datetime);
                        HashMap<String, String> statsMap = new LinkedHashMap<String, String>();


                        statsMap.put("appCode", appCode);
                        statsMap.put("logCounts", counts);
                        statsMap.put("startTime", datetime);
                        statsMap.put("statsRid", rid);
//                      statsMap.put("APP_CODE", tuple._1());
//                      statsMap.put("LOG_COUNTS", tuple._2().toString());

                        //入库
                        String postAddURL = STATS_HOST_ENDPOINT + "/logb/stats/app/add";
                        String postBody = MapJsonConverter.simpleMapToJsonStr(statsMap);
                        HttpUtil.postJson(postAddURL, postBody);
                        System.out.println("=====按应用统计结果插入数据库完成");
                        statsMap.clear();

                    }
                });
                return null;
            }
        });
        //endregion

        //region 按日志类别统计该时段所有异常
        JavaPairDStream<String, Integer> logCountsByType = validLogs.mapToPair(
                //JavaPairDStream<String, Integer> logCountsByApp = logItems.mapToPair(
                new PairFunction<String, String, Integer>() {
                    @Override
                    public Tuple2<String, Integer> call(String s) {
                        String[] flatLogs = s.split(",");
                        String type = flatLogs[8].split(":")[1].replace("\"", "");
                        System.out.println("=====统计的type是 " + type);
                        return new Tuple2<>(type, 1);
                    }
                })
                .reduceByKeyAndWindow(new Function2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer i1, Integer i2) {
                        //System.out.println("累加处理 " + i1);
                        return i1 + i2;
                    }
                }, new Duration(5000), new Duration(5000));

        //按日志类别统计结果存入mysql
        logCountsByType.foreach(new Function2<JavaPairRDD<String, Integer>, Time, Void>() {
            @Override
            public Void call(JavaPairRDD<String, Integer> values, Time time)
                    throws Exception {
                values.foreach(new VoidFunction<Tuple2<String, Integer>>() {
                    @Override
                    public void call(Tuple2<String, Integer> tuple) throws Exception {
                        String logType = tuple._1();
                        String counts = tuple._2().toString();
                        String datetime = DateHelper.getFullStandardDate();
                        String rid = StatsIdBuilder.build(logType, datetime);
                        HashMap<String, String> statsMap = new LinkedHashMap<String, String>();
                        statsMap.put("logCounts", counts);
                        statsMap.put("logType", logType);
                        statsMap.put("startTime", datetime);
                        statsMap.put("statsRid", rid);

                        //入库
                        String postAddURL = STATS_HOST_ENDPOINT + "/logb/stats/type/add";
                        String postBody = MapJsonConverter.simpleMapToJsonStr(statsMap);

                        HttpUtil.postJson(postAddURL, postBody);
                        System.out.println("=====按类型统计结果插入数据库完成");

                        statsMap.clear();

                    }
                });
                return null;
            }
        });
        //endregion

        //region 按日志级别统计该时段日志
        JavaPairDStream<String, Integer> logCountsByLevel = validLogs.mapToPair(
                new PairFunction<String, String, Integer>() {
                    @Override
                    public Tuple2<String, Integer> call(String s) {
                        String[] flatLogs = s.split(",");
                        String level = flatLogs[1].split(":")[1].replace("\"", "");
                        System.out.println("=====统计的type是 " + level);

                        //错误日志入库+消息通知
                        if (level.equals("ERROR")) {

                            System.out.println("=====错误日志是 " + s);

                            HashMap<String, String> logMap = (HashMap) MapJsonConverter.simpleJsonStrToMap(s);
                            String appCode = logMap.get("appCode");
                            String logTime = logMap.get("logTime");
                            String formattedTime = logTime.replace(" ", "T").replace(",", ".") + "Z";
                            String logId = logMap.get("logId");
                            String notes = logMap.get("logLevel") + " ;;; " + logMap.get("logType");
                            String rid = StatsIdBuilder.build(notes, DateHelper.getFullStandardDate());

                            HashMap<String, String> errMap = new LinkedHashMap<>();
                            errMap.put("appCode", appCode);
                            errMap.put("logId", logId);
                            errMap.put("logTime", formattedTime);
                            errMap.put("notes", notes);
                            errMap.put("statsRid", rid);

                            //入库
                            String postAddURL = STATS_HOST_ENDPOINT + "/logb/exp/add";
                            String postBody = MapJsonConverter.simpleMapToJsonStr(errMap);
                            HttpUtil.postJson(postAddURL, postBody);
                            System.out.println("=====异常日志统计结果插入数据库完成");
                            errMap.clear();

                        }

                        return new Tuple2<>(level, 1);
                    }
                })
                .reduceByKeyAndWindow(new Function2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer i1, Integer i2) {
                        //System.out.println("累加处理 " + i1);
                        return i1 + i2;
                    }
                }, new Duration(5000), new Duration(5000));

        //按级别统计结果存入mysql
        logCountsByLevel.foreach(new Function2<JavaPairRDD<String, Integer>, Time, Void>() {
            @Override
            public Void call(JavaPairRDD<String, Integer> values, Time time)
                    throws Exception {
                values.foreach(new VoidFunction<Tuple2<String, Integer>>() {
                    @Override
                    public void call(Tuple2<String, Integer> tuple) throws Exception {
                        String logLevel = tuple._1();
                        String counts = tuple._2().toString();
                        String datetime = DateHelper.getFullStandardDate();
                        String rid = StatsIdBuilder.build(logLevel, datetime);
                        HashMap<String, String> statsMap = new LinkedHashMap<String, String>();
                        statsMap.put("logCounts", counts);
                        statsMap.put("logLevel", logLevel);
                        statsMap.put("startTime", datetime);
                        statsMap.put("statsRid", rid);
                        String postAddURL = STATS_HOST_ENDPOINT + "/logb/stats/level/add";
                        String postBody = MapJsonConverter.simpleMapToJsonStr(statsMap);
                        HttpUtil.postJson(postAddURL, postBody);
                        System.out.println("=====按级别统计结果插入数据库完成");

                        statsMap.clear();
                    }
                });
                return null;
            }
        });
        //endregion

        jssc.start();
        jssc.awaitTermination();

    }
}
