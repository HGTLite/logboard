package com.hgt.redis.consumer;

import com.hgt.msg.HttpUtil;
import com.hgt.utils.CacheThreadHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.hgt.ip.URLBuilder.buildHttpHostEndpoint;

@Component
public class ExpConsumer {

    private static final Logger log = LoggerFactory.getLogger(ExpConsumer.class);

    //    private final String SOCKET_HOST_ENDPOINT = buildHttpHostEndpoint("192.168.99.75", "8701");
    private final String SOCKET_HOST_ENDPOINT = buildHttpHostEndpoint("localhost", "8701");
    //    private final String STATS_HOST_ENDPOINT = buildHttpHostEndpoint("192.168.99.75", "8702");
    private final String STATS_HOST_ENDPOINT = buildHttpHostEndpoint("localhost", "8702");

    public void messageHandler(String message) {

        log.info(" ===成功消费消息 message> " + message);

        //region 异常计数发送到socket
        String targetServerURL = SOCKET_HOST_ENDPOINT + "/send/log-exp-streaming";
        Map params1 = new HashMap();
        params1.put("message", message);
        HttpUtil.post(targetServerURL, params1, 3000, 3000, "UTF-8");
        //endregion 异常计数发送到socket

        //region 异常计数加N
        String targetSreverCounts = STATS_HOST_ENDPOINT + "/logb/counts/update/counts/expCountsStreaming/" + message;
        Map params2 = new HashMap();
        CacheThreadHelper.newThreadGetByMap(targetSreverCounts, params2);
        //endregion 异常计数加N

    }


}


