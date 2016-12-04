package com.hgt.redis.consumer;

import com.hgt.msg.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StringConsumer {

    private static final Logger log = LoggerFactory.getLogger(StringConsumer.class);

    private final String socketHostIP = "192.168.99.75";
    private final String socketHost = "http://" + socketHostIP + ":8701";

    public void messageHandler(String message) {

        log.info(" ===成功消费消息 Consumer> " + message);

        //发送到socket

        String targetServerURL = socketHost + "/send/log-counts-streaming";

        Map params = new HashMap();
        params.put("message", "hello, b**ches2");
        String str = HttpUtil.post(socketHost, params, 3000, 3000, "UTF-8");

        System.out.println("消息转发到：" + targetServerURL);


    }


}
