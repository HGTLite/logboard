package com.hgt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsgRouterApp {

    @Value("${topic}")
    String topic;

    public static void main(String[] args) {
        SpringApplication.run(MsgRouterApp.class, args);
        System.out.println("===MsgRouterApp 启动完成===");

    }


}