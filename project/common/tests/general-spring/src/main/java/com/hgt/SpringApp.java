package com.hgt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@SpringBootApplication
public class SpringApp
{
    @RequestMapping("/hello/{msg}")
    public String Home(@PathVariable String msg) {
        return "hello" + msg;
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @Value("${configProp:yaoName}")
    private String name;

    public static void main(String[] args) {

        SpringApplication.run(SpringApp.class, args);
        System.out.println("===SpringApp 启动完成===");

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

            //日志条数
            int ii = 0;

            @Override
            public void run() {
                if (ii % 2 == 0) {
//                    producer.sendTo("spring-boot", "测试 " + ii + " 消息 hello");
                    System.out.println("hello, even");
                    ii++;
                } else {
//                    producer.sendTo("spring-boot", "测试 " + ii + " 消息 hello");
                    System.out.println("hello, odd");
                    ii++;
                }
            }
        }, 5, 4, TimeUnit.SECONDS);
    }

    @Bean
    CommandLineRunner sendMessage() {
        return args -> {
            System.out.println("===topic是 " + this.name);
//            producer.sendTo(topic, "Spring Boot rocks with Redis messaging!");
        };
    }


}
