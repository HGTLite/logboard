package com.hgt.redis.consumer;

import com.hgt.converter.MapJsonConverter;
import com.hgt.msg.HttpUtil;
import com.hgt.utils.CacheThreadHelper;
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

    //    private final String SOCKET_HOST_ENDPOINT = buildHttpHostEndpoint("192.168.99.75", "8701");
    private final String SOCKET_HOST_ENDPOINT = buildHttpHostEndpoint("localhost", "8701");
    //    private final String STATS_HOST_ENDPOINT = buildHttpHostEndpoint("192.168.99.75", "8702");
    private final String STATS_HOST_ENDPOINT = buildHttpHostEndpoint("localhost", "8702");

    public void messageHandler(String message) {

        log.info(" ===成功消费消息 message> " + message);

        Map<String, String> msgMap = new HashMap<>();
        msgMap = MapJsonConverter.simpleJsonStrToMap(message);
        String msgTag = msgMap.get("msgTag");
        String msgBody = msgMap.get("msgBody");


        switch (msgTag) {
            case "STREAMING_LOG_COUNTS":
                sendToSocketLogCounts(msgBody);
                break;
            case "STREAMING_EXP_COUNTS":
                sendToSocketExpCounts(msgBody);
                break;
            default:
                System.out.println("消息找不到目标话题");
        }

    }

    public void sendToSocketLogCounts(String strMsg) {
        //region 日志计数发送到socket
        String targetServerURL = SOCKET_HOST_ENDPOINT + "/send/log-counts-streaming";
        Map params1 = new HashMap();
        params1.put("message", strMsg);
        HttpUtil.post(targetServerURL, params1, 3000, 3000, "UTF-8");
        //endregion

        //region 日志计数入库
        String targetSreverCounts = STATS_HOST_ENDPOINT + "/logb/counts/update/counts/logs/logCountsStreaming/" + strMsg;
        Map params2 = new HashMap();
        CacheThreadHelper.newThreadGetByMap(targetSreverCounts, params2);
        //endregion
    }

    public void sendToSocketExpCounts(String strMsg) {
        //region 异常计数发送到socket
        String targetServerURL = SOCKET_HOST_ENDPOINT + "/send/log-exp-streaming";
        Map params1 = new HashMap();
        params1.put("message", strMsg);
        HttpUtil.post(targetServerURL, params1, 3000, 3000, "UTF-8");
        //endregion 异常计数发送到socket

        //region 异常计数加N
        String targetSreverCounts = STATS_HOST_ENDPOINT + "/logb/counts/update/counts/exp/expCountsStreaming/" + strMsg;
        Map params2 = new HashMap();
        CacheThreadHelper.newThreadGetByMap(targetSreverCounts, params2);
        //endregion 异常计数加N
    }


}


