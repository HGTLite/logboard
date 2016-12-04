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

    private final String SOCKET_HOST_IP = "192.168.99.75";
    private final String SOCKET_HOST_ENDPOINT = "http://" + SOCKET_HOST_IP + ":8701";

    public void messageHandler(String message) {

        log.info(" ===成功消费消息 Consumer> " + message);

        //发送到socket

        String targetServerURL = SOCKET_HOST_ENDPOINT + "/send/log-counts-streaming";

        Map params = new HashMap();
        params.put("message", message);
        String str = HttpUtil.post(targetServerURL, params, 3000, 3000, "UTF-8");




        System.out.println("消息转发到：" + targetServerURL);


    }


}
