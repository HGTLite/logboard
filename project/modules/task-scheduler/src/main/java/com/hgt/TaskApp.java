package com.hgt;


import com.hgt.heartbeat.HeartBeatServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TaskApp {

    public static void main(String[] args) {

        SpringApplication.run(TaskApp.class, args);

        System.out.println("开始检测客户端是否在线...");
        new HeartBeatServer().start();

    }


}
