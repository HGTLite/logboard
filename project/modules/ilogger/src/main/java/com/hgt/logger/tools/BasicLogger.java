package com.hgt.logger.tools;

import com.hgt.logger.formats.LogKeyInfo;
import com.hgt.utils.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/******************************************************************************
 * Created by  Yao  on  2016/7/4
 * README:日志打印工具类
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
@SuppressWarnings("unused")
public class BasicLogger {

    //日志必需内容
    private LogKeyInfo logKeyInfo;
    //日志可选内容
    private HashMap<String,String> logOptions;
    //实际日志打印对象
    private Logger logger;


    //javabean转json对象
    private JsonHelper jsonHelper;

    //产生GENERAL型日志
    public BasicLogger(Class c) {
        logKeyInfo = new LogKeyInfo("General");
        jsonHelper = new JsonHelper();
        logger = LoggerFactory.getLogger(c);
    }

    //指定日志类别，输入值参考LogTags枚举
    public BasicLogger(Class c, String type) {
        logKeyInfo = new LogKeyInfo(type);
        jsonHelper = new JsonHelper();
        logger = LoggerFactory.getLogger(c);
    }

    //指定日志类别和日志内容
    public BasicLogger(Class c, String type, String msg) {
        logKeyInfo = new LogKeyInfo(type, msg);
        jsonHelper = new JsonHelper();
        logger = LoggerFactory.getLogger(c);
    }

    //指定所有日志内容
//    BasicLogger(Class c, String intAppcode, String strUserId, String strUserIp, String strType, String m, String strCommentsField) {
//        logKeyInfo = new LogKeyInfo(intAppcode, strUserId, strUserIp, strType, m, strCommentsField);
//        jsonHelper = new JsonHelper();
//        logger = LoggerFactory.getLogger(c);
//    }


    public HashMap<String, String> getLogOptions() {
        return logOptions;
    }

    public void setLogOptions(HashMap<String, String> logOptions) {
        this.logOptions = logOptions;
    }

    public void debug(String strP) {
        logKeyInfo.setLogMsg(strP);
        logger.debug(jsonHelper.javaBean2JsonString(logKeyInfo));
    }

    public void info(String strP) {
        logKeyInfo.setLogMsg(strP);
        logger.info(jsonHelper.javaBean2JsonString(logKeyInfo));
    }

    public void warn(String strP) {
        logKeyInfo.setLogMsg(strP);
        logger.warn(jsonHelper.javaBean2JsonString(logKeyInfo));
    }

    public void error(String strP, Exception e1) {
        logKeyInfo.setLogMsg(strP + "=====e====" + e1.toString());
        logger.error(jsonHelper.javaBean2JsonString(logKeyInfo), e1);
    }

}
