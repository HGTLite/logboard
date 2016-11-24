package com.hgt;

import com.hgt.redis.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsgRouterApp {

	@Value("${topic}")
	  String topic;

	public static void main(String[] args)
	{
		System.out.println("===1 before boot");
		SpringApplication.run(MsgRouterApp.class, args);
		System.out.println("===2 after boot");

	}
	
//	@Bean
//    CommandLineRunner sendMessage(Producer producer){
//		return args -> {
////			System.out.println("===topic是 "+this.topic);
//			producer.sendTo(topic, "Spring Boot rocks with Redis messaging!");
//		};
//	}

}
