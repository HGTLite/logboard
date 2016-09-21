package com.hgt.utils;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 * Created by  Yao  on  2016/7/14
 * README:工具类 for 字符串
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class StringHelper {

    ///长字符串转List
    public static List<String> strTOList(String str) {
        List<String> result = new ArrayList<String>();
        String[] strArr = str.split(",");

        for (int i = 0; i < strArr.length; i++) {
            result.add(strArr[i]);
        }

        return result;
    }

    public static String joinHostPort(String hostList, String port) {
        String result = "";

        String[] hostArr = hostList.split(",");
        for (int i = 0; i < hostArr.length; i++) {
            result += hostArr[i] + ":" + port;
            if (i != (hostArr.length - 1)) {
                result += ",";
            }
        }

        return result;
    }


}
