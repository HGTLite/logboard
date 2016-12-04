package com.hgt.redis.consumer;

import com.hgt.msg.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StringConsumer {
    private static final Logger log = LoggerFactory.getLogger(StringConsumer.class);

    public void messageHandler(String message) {

        log.info(" ===成功消费消息 Consumer> " + message);

        //发送到socket

        String socketHost = "http://localhost:8701/send/testDiv";

        String msgJson = message;

        String str = HttpUtil.postJson(socketHost, message);

    }

}
