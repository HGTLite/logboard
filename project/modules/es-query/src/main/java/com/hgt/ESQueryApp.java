package com.hgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * ES查询入口
 */
@SpringBootApplication
public class ESQueryApp {

    public static void main(String[] args) {

        SpringApplication.run(ESQueryApp.class, args);
        System.out.println("===ESQueryApp 启动完成===");

    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/logb/**").allowedOrigins("*");
//                registry.addMapping("/lb/apps/all/{pageNum}/{pageSize}").allowedOrigins("*");

            }


        };
    }
}