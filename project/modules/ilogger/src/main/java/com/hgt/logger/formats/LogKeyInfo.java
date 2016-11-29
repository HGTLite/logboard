package com.hgt.logger.formats;

import java.util.HashMap;

import static com.hgt.logger.formats.LogType.GENERAL;

/******************************************************************************
 * Created by  Yao  on  2016/7/4
 * README:日志内容格式拼凑类
 * 必填：AppCode-默认8位00000000表示未知，LogType-默认为GENERAL表示一般，LogMsg-默认为"LOG-AT-CLASS.NAME"
 * 选填：logOptions
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
@SuppressWarnings("unused")
public class LogKeyInfo {

    //日志ID
    private String beating;
    //应用ID，默认为00000000
    private String appCode;
    //日志类别，默认为GENERAL
    private LogType logType;
    //日志内容
    private String logMsg;
    //日志其他信息
    private HashMap<String, String> logOptions;

    //默认无参构造函数
    public LogKeyInfo() {
//      this("000000","GENERAL","LOG-AT-" + this.getClass().getName(),new HashMap<>());
        this.appCode = "00000000";
        this.logType = GENERAL;
        this.logMsg = "LOG-AT-" + this.getClass().getName();
        this.logOptions = new HashMap<>();
    }

    //指定日志所在应用程序的AppCode
    public LogKeyInfo(String StrAppCode) {
        this.appCode = StrAppCode;
        this.logType = GENERAL;
        this.logMsg = "LOG-AT-" + this.getClass().getName();
        this.logOptions = new HashMap<>();
    }

    //指定日志类别的构造函数，输入值参考LogType枚举
    public LogKeyInfo(String StrAppCode, String type) {
        this.appCode = StrAppCode;
        this.logType = LogType.valueOf(type.toUpperCase());
        this.logMsg = "LOG-AT-" + this.getClass().getName();
        this.logOptions = new HashMap<>();
    }

    //指定日志类别和内容的构造函数，输入值参考LogType枚举
    public LogKeyInfo(String StrAppCode, String type, String strLogMsg) {
        this.appCode = StrAppCode;
        this.logType = LogType.valueOf(type.toUpperCase());
        this.logMsg = strLogMsg;
        this.logOptions = new HashMap<>();
    }

    //指定所有属性的构造函数
    public LogKeyInfo(String strAppcode, String type, String strLogMsg, HashMap<String, String> options) {
        this.appCode = strAppcode;
        this.logType = LogType.valueOf(type.toUpperCase());
        this.logMsg = strLogMsg;
        this.logOptions = options;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public String getLogMsg() {
        return logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }

    public HashMap<String, String> getLogOptions() {
        return logOptions;
    }

    public void setLogOptions(HashMap<String, String> logOptions) {
        this.logOptions = logOptions;
    }

}
