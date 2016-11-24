package com.hgt.bak;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class App implements CommandLineRunner {

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

    @Override
    public void run(String... args) {
        System.out.println("===配置参数是=== " + this.name);

    }

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

//        System.out.println();

    }



}
