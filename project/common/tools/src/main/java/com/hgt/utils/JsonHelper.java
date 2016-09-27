package com.hgt.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/******************************************************************************
 * Created by  Yao  on  2016/7/3
 * README:工具类 for JSON
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class JsonHelper {

///Bean转json格式的字符串
    public  String convertBean2Json(Object o, String strP) {
        String str = strP;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            str += objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;
    }


    public  String convertBean2Json(Object o) {
        return this.convertBean2Json(o, "");
    }

}
