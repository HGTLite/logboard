package com.hgt.utils;

import java.util.*;

/******************************************************************************
 * Created by  Yao  on  2016/7/14
 * README:工具类 for 字符串
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class StringHelper {

    /**
     * 长字符串转List
     *
     * @param str
     * @return
     */
    public static List<String> strToList(String str) {
        List<String> result = new ArrayList<String>();
        String[] strArr = str.split(",");

        for (int i = 0; i < strArr.length; i++) {
            result.add(strArr[i]);
        }

        return result;
    }

    /**
     * 多个主机相同端口的host_endpoint拼凑
     *
     * @param hostList
     * @param port
     * @return
     */
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

    /**
     * 将长字符串转换成元素为HashMap<String,String>的List
     * 例：将字符串转换成IP列表方便取用, "192.168.100.240:2181,192.168.100.241:2181"转换成List<HashMap<ip,port>>
     *
     * @param strLong
     * @return
     */
    public static List<HashMap<String, String>> getListFromString(String strLong) {

        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        String[] listArr = strLong.split(",");
        for (int i = 0; i < listArr.length; i++) {
            HashMap<String, String> m = new HashMap<>();
            String[] kAndV = listArr[i].split(":");
            m.put("kk", kAndV[0]);
            m.put("vv", kAndV[1]);
            list.add(m);
        }

        return list;
    }

}
