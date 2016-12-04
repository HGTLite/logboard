package com.hgt.redis.consumer;

import com.hgt.msg.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.hgt.ip.URLBuilder.buildHttpHostEndpoint;

@Component
public class StringConsumer {

    private static final Logger log = LoggerFactory.getLogger(StringConsumer.class);

    private final String SOCKET_HOST_ENDPOINT = buildHttpHostEndpoint("192.168.99.75", "8701");
    private final String STATS_HOST_ENDPOINT = buildHttpHostEndpoint("192.168.99.75", "8702");

    public void messageHandler(String message) {

        log.info(" ===成功消费消息 Consumer> " + message);

        //region 日志计数发送到socket
        String targetServerURL = SOCKET_HOST_ENDPOINT + "/send/log-counts-streaming";
        Map params = new HashMap();
        params.put("message", message);
          HttpUtil.post(targetServerURL, params, 3000, 3000, "UTF-8");
        //endregion

        //region 日志计数入库
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                String targetSreverCounts = STATS_HOST_ENDPOINT + "/logb/counts/update/counts/logCountsStreaming/" + message;
                Map params = new HashMap();
                HttpUtil.get(targetSreverCounts, params, 3000, 2000, "UTF-8");
            }
        });
        //endregion

    }


}


