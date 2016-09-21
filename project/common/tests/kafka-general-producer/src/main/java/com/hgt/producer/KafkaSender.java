package com.hgt.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/******************************************************************************
 * Created by  Yao  on  2016/9/21
 * README: 
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class KafkaSender {

    static KafkaProducer<String, String> producer;
    String topicName = "topic-hello";

    public static void main(String args[]) {

        try {

            Properties producerProperties = new Properties();
            producerProperties.put("bootstrap.servers", "192.168.99.111:6667,192.168.99.112:6667,192.168.99.113:6667,192.168.99.114:6667");
            producerProperties.put("acks", "all");
            producerProperties.put("retries", 0);
            producerProperties.put("batch.size", 10000);
            producerProperties.put("linger.ms", 1000);
            producerProperties.put("buffer.memory", 5242880);
            producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            producer = new KafkaProducer<>(producerProperties);

            System.out.println("=========START Kafka Producer==========");
            new KafkaSender().runProducer();

        } catch (Exception exception) {
            System.out.println(exception);
        }

    }

    public void runProducer() {
        try {
            for (int i = 10; i < 20; i++) {

                String key = "MessageKey is " + Integer.toString(i);
                String value = "MessageVal is " + Integer.toString(i);
                ProducerRecord record = new ProducerRecord<String, String>(this.topicName, key, value);
                //Publishing the record to topic
                producer.send(record);
                System.out.println("Published Message to " + this.topicName);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        } finally {
            producer.close();
            System.out.println("Kafka Producer Closed");
        }

    }
}
