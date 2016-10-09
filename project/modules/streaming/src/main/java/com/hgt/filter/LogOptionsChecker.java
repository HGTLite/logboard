package com.hgt.filter;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/******************************************************************************
 * Created by  Yao  on  2016/10/8
 * README: 日志选填信息校验器
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class LogOptionsChecker implements ValidLogI {

    HashMap<String, String> logInfoMap;

    public LogOptionsChecker(HashMap<String, String> log) {
        logInfoMap = log;
    }

    public boolean isLogIPValid(String s) {

        boolean flag = false;

        Pattern pattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
        Matcher m = pattern.matcher(s);
        flag = m.matches();

        return flag;
    }




    @Override
    public boolean isLogValid() {
        boolean ret = false;
        if (isLogIPValid(logInfoMap.get("USER_IP"))) {
            ret = true;
        }
        return ret;
    }

}
