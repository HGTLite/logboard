package com.hgt.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hgt.es.tools.ESLogBean;

import java.io.IOException;

/**
 * README:
 * Created by Yao on 12/1/16.
 * =============================================================================
 * CHANGELOG:
 */
public class LogStrToBean {

    public void convertLogStrToBean(String strLog) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            ESLogBean esLogBean = mapper.readValue(strLog, ESLogBean.class);
            System.out.println(esLogBean.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        String log = "{\"logLevel\":\"WARN\",\"logTime\":\"2016-11-30T15:36:08.988Z\",\"codeClass\":\"com.hgt.App\",\"codeFile\":\"BasicLogger.java\",\"lineNumber\":\"163\",\"appCode\":\"hello001\",\"logType\":\"LOGIN\",\"logMsg\":\"这是第 643 条消息！！！\",\"logOptions\":\"{USER_ID:user002,USER_IP:36.58.254.164}\"}";

        new LogStrToBean().convertLogStrToBean(log);
    }

}
