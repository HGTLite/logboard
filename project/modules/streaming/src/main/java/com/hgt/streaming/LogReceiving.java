package com.hgt.streaming;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Pattern;

import com.hgt.filter.LogKeyChecker;
import com.hgt.filter.LogOptionsChecker;
import com.hgt.hbase.common.HBaseOperations;
import com.hgt.hbase.keys.RowkeyFactory;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import scala.Tuple2;
import com.google.common.collect.Lists;

import org.apache.spark.*;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.*;
import org.apache.spark.streaming.*;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

public class LogReceiving {

    public static void main(String[] args) {

//        String zkQuorum = "192.168.99.111:2181,192.168.99.112:2181,192.168.99.113:2181,192.168.99.114:2181";
        String zkQuorum = "192.168.100.241:2181,192.168.100.242:2181,192.168.100.243:2181";
        //话题所在的组
        String group = "test1";
        //话题名称以“，”分隔
        String topics = "topic-hello";
        //每个话题的分片数
        int numThreads = 2;
        SparkConf sparkConf = new SparkConf().setAppName("HelloStreaming").setMaster("local[4]");
        //每5s进行批处理
        JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, new Duration(5000));

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
                return Lists.newArrayList(arg0._2);
            }
        });

        //过滤次要日志，存储有效日志
        JavaDStream<String> validLogs = logItems.filter(new Function<String, Boolean>() {
            @Override
            public Boolean call(String s) throws Exception {

//                //region ES配置
//                Settings settings = Settings.builder()
//                        .put("cluster.name", "es-log")
//                        .put("client.transport.sniff", true)
//                        .build();
//                TransportClient client = null;
//                try {
//                    client = new PreBuiltTransportClient(settings)
//                            .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.100.240"), 9300))
//                            .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.100.241"), 9300))
//                            .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.100.243"), 9300));
//
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                }
//
//                AdminClient adminClient = client.admin();
//                //endregion
//
//                IndexResponse response = client.prepareIndex("hgt-logs", "loginfo")
//                .setSource(s)
//                .get();

                HashMap<String, String> logMap = new LinkedHashMap<>();
                logMap.put("logLevel", s.substring(1, 6).trim());
                logMap.put("logTime", s.substring(7, 30).trim());
                logMap.put("codeClass", s.substring(31, 71).trim());
                logMap.put("codeFile", s.substring(72, 92).trim());
                logMap.put("lineNumber", s.substring(93, 96).trim());

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

//                return new LogKeyChecker(logMap).isLogValid() && new LogOptionsChecker(logMap).isLogValid() ? true : false;
                if (new LogKeyChecker(logMap).isLogValid() && new LogOptionsChecker(logMap).isLogValid()) {
                    List<String> keys = new ArrayList<String>(logMap.keySet());
                    List<String> vals = new ArrayList<String>(logMap.values());
                    String tableName = "hgt-logs";
                    HBaseOperations hBaseOperations = new HBaseOperations();
                    String rk = RowkeyFactory.build16(logMap.get("logType"), logMap.get("logTime"));
                    //TO-DO：分成2个列族存储
                    hBaseOperations.insertRow(tableName, rk, "loginfo", keys, vals);

                    return true;
                } else {
                    return false;
                }
            }
        });


        //对其中的单词进行统计
        JavaPairDStream<String, Integer> wordCounts = validLogs.mapToPair(

                new PairFunction<String, String, Integer>() {
                    @Override
                    public Tuple2<String, Integer> call(String s) {
                        return new Tuple2<>(s, 1);
                    }
                }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer i1, Integer i2) {
                return i1 + i2;
            }
        });

        wordCounts.foreach(new Function2<JavaPairRDD<String, Integer>, Time, Void>() {

            @Override
            public Void call(JavaPairRDD<String, Integer> values, Time time)
                    throws Exception {

                values.foreach(new VoidFunction<Tuple2<String, Integer>>() {

                    @Override
                    public void call(Tuple2<String, Integer> tuple) throws Exception {

                        System.out.println("Tuple1: " + tuple._1() + ", Tuple2: " + tuple._2());

//                        List<String> key = Arrays.asList("wordInfo", "wordCount");
//                        List<String> value = Arrays.asList(tuple._1(), tuple._2().toString());
//
//                        String tableName = "logboard-test";
//                        HBaseOperations hBaseOperations = new HBaseOperations();
//                        String rk = new RowkeyFactory().create3Numbers();
//                        hBaseOperations.insertRow(tableName, rk, "loginfo", key, value);
                    }
                });

                return null;
            }
        });

        jssc.start();
        jssc.awaitTermination();

    }

}
