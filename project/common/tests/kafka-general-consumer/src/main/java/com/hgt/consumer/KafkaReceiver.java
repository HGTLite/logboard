package com.hgt.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/******************************************************************************
 * Created by  Yao  on  2016/9/21
 * README: 
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class KafkaReceiver {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.99.111:6667,192.168.99.112:6667,192.168.99.113:6667,192.168.99.114:6667");
        props.put("group.id", "test20");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(
                props);
        consumer.subscribe(Arrays.asList("topic-hello"));

        while (true) {

            ConsumerRecords<String, String> records = consumer.poll(1000);

            if (records.isEmpty()) {
                System.out.println("No data to Consume");
            } else {
                //Iterating the consumer records, to extract the data from topic
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf(" offset = %d, key = %s, value = %s \n", record.offset(), record.key(), record.value());
                }
            }


        }
    }

}
