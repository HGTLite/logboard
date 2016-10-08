package com.hgt.filter;

import java.util.HashMap;

/******************************************************************************
 * Created by  Yao  on  2016/10/8
 * README: 日志主要信息校验器
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class LogKeyChecker implements ValidLogI {

    HashMap<String, String> logInfoMap;

    public LogKeyChecker(HashMap<String, String> log) {
        logInfoMap = log;
    }

    public boolean isLogLevelValid(String s) {
        boolean flag = false;
        if (!(s.toUpperCase().equals("DEBUG") || s.toUpperCase().equals("TRACE"))) {
            flag = true;
        }
        return flag;
    }


    public boolean isLogTypeValid(String s) {
        boolean flag = false;
        if (!(s.toUpperCase().equals("TEST"))) {
            flag = true;
        }
        return flag;
    }


    @Override
    public boolean isLogValid() {
        boolean ret = false;
        if (isLogLevelValid(logInfoMap.get("logLevel")) && isLogTypeValid(logInfoMap.get("logType"))) {
            ret = true;
        }
        return ret;
    }

}
