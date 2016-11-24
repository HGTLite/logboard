package com.hgt;

import com.hgt.redis.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootRedisApplication {

	public static void main(String[] args)
	{
		System.out.println("before boot");
		SpringApplication.run(SpringBootRedisApplication.class, args);
		System.out.println("after boot");

	}
	
//	@Value("${topic}")
//	String topic;
//
//	@Bean
//    CommandLineRunner sendMessage(Producer producer){
//		return args -> {
//			producer.sendTo(topic, "Spring Boot rocks with Redis messaging!");
//		};
//	}
}
