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

                //=========================================GET================================
//                registry.addMapping("/photos/wall").allowedOrigins("*");
//                registry.addMapping("/photos/wall/all").allowedOrigins("*");
//                registry.addMapping("/photos/id/{photoId}").allowedOrigins("*");
//                registry.addMapping("/photos/user/{userInfo:.+}").allowedOrigins("*");
//                registry.addMapping("/photos/user/{userInfo:.+}/{page}/{pageSize}").allowedOrigins("*");
//                registry.addMapping("/photos/all").allowedOrigins("*");
//                registry.addMapping("/photos/all/{page}/{pageSize}").allowedOrigins("*");
//                registry.addMapping("/stu/all").allowedOrigins("*");
//                registry.addMapping("/users/all/{page}/{pageSize}").allowedOrigins("*");
//                registry.addMapping("/users/id/{userId}").allowedOrigins("*");
//                registry.addMapping("/users/login/hash/{userName:.+}").allowedOrigins("*");
//
//                //=========================================POST===============================
//                registry.addMapping("/photos/add").allowedOrigins("*");
//                registry.addMapping("/photos/upload").allowedOrigins("*");
//                registry.addMapping("/photos/uploads").allowedOrigins("*");
//                registry.addMapping("/users/login").allowedOrigins("*");
//                registry.addMapping("/users/add").allowedOrigins("*");
            }
        };
    }
}
