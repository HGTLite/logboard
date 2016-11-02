package com.hgt.utils;

import java.util.*;

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

    /**
     * 将字符串转换成IP列表方便取用
     * 例："192.168.100.240:2181,192.168.100.241:2181"转换成List<HashMap<ip,port>>
     * @param strIP
     * @return
     */
    public static List<HashMap<String,String>> getIPListFromString(String strIP){
        List<HashMap<String,String>> ipList=new ArrayList<HashMap<String,String>>();

        String[] ipArr=strIP.split(",");
        for (int i=0;i<ipArr.length;i++){
            HashMap<String,String> singleIp =new HashMap<>();
            String[] ipAndPort = ipArr[i].split(":");
            singleIp.put("ip",ipAndPort[0]);
            singleIp.put("port",ipAndPort[1]);
            ipList.add(singleIp);
        }

        return ipList;
    }

}
