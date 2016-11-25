package com.hgt;

import com.hgt.redis.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MsgProducerApp {

    private static final Logger log = LoggerFactory.getLogger(MsgProducerApp.class);

    @Value("${topic}")
    String topic;

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }


    public static void main(String[] args) {

        SpringApplication.run(MsgProducerApp.class, args);
        System.out.println("===MsgProducerApp 启动完成===");

        ApplicationContext ctx = SpringApplication.run(MsgProducerApp.class, args);
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        template.convertAndSend("spring-boot", "Hello from Redis!");
        System.out.println("===redis消息发送成功===");

    }


}
