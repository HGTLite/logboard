package com.hgt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class MsgRouterApp {

    @Value("${topic}")
    String topic;

    public static void main(String[] args) {

        SpringApplication.run(MsgRouterApp.class, args);
        System.out.println("===MsgRouterApp 启动完成===");

    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/msg/**").allowedOrigins("*");
//                registry.addMapping("/lb/apps/all/{pageNum}/{pageSize}").allowedOrigins("*");

            }


        };
    }

}
