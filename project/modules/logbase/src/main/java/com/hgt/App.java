package com.hgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/******************************************************************************
 * Created by  Yao  on  2016/7/17
 * README:程序入口类
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/

@SpringBootApplication
public class App {

    public static void main(String[] args) {

        System.out.println("=====Main Method BEGIN=====");
        SpringApplication.run(App.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                //===============================/lb/apps=============================
                registry.addMapping("/lb/apps/id/{laId}").allowedOrigins("*");
                registry.addMapping("/lb/apps//all").allowedOrigins("*");
                registry.addMapping("/lb/apps/all/{pageNum}/{pageSize}").allowedOrigins("*");
                registry.addMapping("/lb/apps/add").allowedOrigins("*");
                registry.addMapping("/lb/apps/delete/{laId}").allowedOrigins("*");

            }



        };
    }
}
