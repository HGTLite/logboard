package com.hgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogBootApp {

	public static void main(String[] args) {

		SpringApplication.run(LogBootApp.class, args);
		System.out.println("===LogBootApp 启动完成===");

	}
}
