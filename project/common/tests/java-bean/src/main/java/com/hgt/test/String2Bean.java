package com.hgt.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hgt.bean.ManualLogBean;

import java.io.IOException;

/**
 * Created by root on 11/6/16.
 */
public class String2Bean {

    public static void main(String[] args) {

        ManualLogBean sampleBean = new ManualLogBean();
        sampleBean.setAppCode("app001");
        sampleBean.setLogMsg("hello, msg");

        ObjectMapper mapper = new ObjectMapper();

        String strBean2Str = "";

//        try {
//            strBean2Str = mapper.writeValueAsString(sampleBean);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("转换后的json字符串是：" + strBean2Str);

        strBean2Str = "{\"appCode\":\"hello1\",\"logType\":\"LOGIN\",\"logMsg\":\"delay 5 seconds 首次延迟5s执行 and excute every 4 seconds 每次间隔4s执行\",\"logOptions\":\"    {\"USER_ID\":\"user001\",\"USER_IP\":\"123.232.179.105\"}    \"";

        ManualLogBean reconstructedBean = null;
        try {
            reconstructedBean = mapper.readValue(strBean2Str, ManualLogBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("转换后的bean是：" + reconstructedBean.toString());

    }

}

