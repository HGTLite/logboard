package com.hgt.ip;

/**
 * README: URL构造器
 * Created by Yao on 12/4/16.
 * =============================================================================
 * CHANGELOG:
 */
public class URLBuilder {

    public static String buildHttpHostEndpoint(String hostIp, String port) {
        String resultStr = "";
        resultStr = "http://" + hostIp + ":" + port;
        return resultStr;
    }

}
