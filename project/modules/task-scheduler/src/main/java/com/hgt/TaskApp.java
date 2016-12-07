package com.hgt;


import com.hgt.heartbeat.HeartBeatServer;
import com.hgt.ip.URLBuilder;
import com.hgt.msg.HttpUtil;
import com.hgt.utils.CacheThreadHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class TaskApp {

    private static final String SCHEDULED_HOST = URLBuilder.buildHttpHostEndpoint("localhost", "8705");

    public static void main(String[] args) {

        SpringApplication.run(TaskApp.class, args);
        System.out.println("===TaskApp 启动完成===");

        System.out.println("开始检测客户端是否在线...");
        new HeartBeatServer().start();

        String targetURL = SCHEDULED_HOST + "/logb/stats/counts/5s";
        Map mapParams = new HashMap();
        CacheThreadHelper.newThreadGetByMap(targetURL, mapParams);



    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**").allowedOrigins("*");

            }

        };
    }


}
