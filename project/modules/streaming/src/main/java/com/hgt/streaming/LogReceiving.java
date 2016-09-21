package com.hgt.streaming;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.hgt.hbase.common.HBaseOperations;
import com.hgt.hbase.keys.RowkeyFactory;
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

/******************************************************************************
 * Created by  Yao  on  2016/9/21
 * README: 
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class LogReceiving {

    public static void main(String[] args) {

        //设置匹配模式，以空格分隔
        final Pattern SPACE = Pattern.compile(" ");
        //接收数据的地址和端口
        String zkQuorum = "192.168.99.111:2181,192.168.99.112:2181,192.168.99.113:2181,192.168.99.114:2181";
        //话题所在的组
        String group = "test1";
        //话题名称以“，”分隔
//        String topics = "top1,top2";
        String topics = "topic-hello";
        //每个话题的分片数
        int numThreads = 2;
        SparkConf sparkConf = new SparkConf().setAppName("StreamingHello").setMaster("local[2]");
        JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, new Duration(10000));
//        jssc.checkpoint("checkpoint"); //设置检查点
        //存放话题跟分片的映射关系
        Map<String, Integer> topicmap = new HashMap<>();
        String[] topicsArr = topics.split(",");
        int n = topicsArr.length;
        for (int i = 0; i < n; i++) {
            topicmap.put(topicsArr[i], numThreads);
        }
        //从Kafka中获取数据转换成RDD
        JavaPairReceiverInputDStream<String, String> lines = KafkaUtils.createStream(jssc, zkQuorum, group, topicmap);
        //从话题中过滤所需数据
        JavaDStream<String> words = lines.flatMap(new FlatMapFunction<Tuple2<String, String>, String>() {

            @Override
            public Iterable<String> call(Tuple2<String, String> arg0)
                    throws Exception {
                return Lists.newArrayList(SPACE.split(arg0._2));
            }
        });
        //对其中的单词进行统计
        JavaPairDStream<String, Integer> wordCounts = words.mapToPair(
                new PairFunction<String, String, Integer>() {
                    @Override
                    public Tuple2<String, Integer> call(String s) {
                        return new Tuple2<String, Integer>(s, 1);
                    }
                }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer i1, Integer i2) {
                return i1 + i2;
            }
        });

        //打印结果
        wordCounts.print();

        wordCounts
                .foreach(new Function2<JavaPairRDD<String, Integer>, Time, Void>() {

                    @Override
                    public Void call(JavaPairRDD<String, Integer> values, Time time)
                            throws Exception {

                        values.foreach(new VoidFunction<Tuple2<String, Integer>>() {

                            @Override
                            public void call(Tuple2<String, Integer> tuple) throws Exception {

                                System.out.println("Tuple1: " + tuple._1() + ", Tuple2: " + tuple._2());

                                String[] key = {"wordInfo", "wordCount"};
                                String[] value = {tuple._1(),tuple._2().toString()};

                                String tableName="logboard-test";
                                HBaseOperations hBaseOperations = new HBaseOperations();
                                String rk = new RowkeyFactory().create3Numbers();
                                hBaseOperations.insertRow(tableName, rk, "loginfo", key, value);

                            }
                        });

                        return null;
                    }
                });


//        wordCounts.dstream().saveAsTextFiles("hdfs://master:9000/testFile/", "spark");

        jssc.start();
        jssc.awaitTermination();

    }

}
