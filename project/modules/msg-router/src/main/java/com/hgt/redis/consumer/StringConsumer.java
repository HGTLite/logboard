package com.hgt.redis.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StringConsumer {
	private static final Logger log = LoggerFactory.getLogger(StringConsumer.class);
	
	public void messageHandler(String message) {

        log.info(" ===成功消费消息 Consumer> " + message);

    }
}
