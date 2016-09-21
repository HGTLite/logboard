package com.hgt.logger.tools;

import com.hgt.logger.info.LogBean;
import com.hgt.utils.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/******************************************************************************
 * Created by  Yao  on  2016/7/4
 * README:日志打印工具类
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class StandardLogger {

    //日志内容
    public LogBean logBean;
    //实际日志打印对象
    private Logger logger;
    //javabean转json对象
    private JsonHelper jsonHelper;

    //产生GENERAL型日志
    public StandardLogger(Class c) {
        logBean = new LogBean("general");
        jsonHelper = new JsonHelper();
        logger = LoggerFactory.getLogger(c);
    }

    //指定日志类别，输入值参考LogTags枚举
    public StandardLogger(Class c, String type) {
        logBean = new LogBean(type);
        jsonHelper = new JsonHelper();
        logger = LoggerFactory.getLogger(c);
    }

    //指定日志类别和日志内容
    public StandardLogger(Class c, String type, String msg) {
        logBean = new LogBean(type, msg);
        jsonHelper = new JsonHelper();
        logger = LoggerFactory.getLogger(c);
    }

    //指定所有日志内容
    StandardLogger(Class c, int intAppcode, String strUserId, String strUserIp, String strType, String m, String strCommentsField) {
        logBean = new LogBean(intAppcode, strUserId, strUserIp, strType, m, strCommentsField);
        jsonHelper = new JsonHelper();
        logger = LoggerFactory.getLogger(c);
    }

    public void debug(String strP) {
        logBean.setMsg(strP);
        logger.debug(jsonHelper.javaBean2JsonString(logBean));
    }

    public void info(String strP) {
        logBean.setMsg(strP);
        logger.info(jsonHelper.javaBean2JsonString(logBean));
    }

    public void warn(String strP) {
        logBean.setMsg(strP);
        logger.warn(jsonHelper.javaBean2JsonString(logBean));
    }

    public void error(String strP, Exception e1) {
        logBean.setMsg(strP + "=====e====" + e1.toString());
        logger.error(jsonHelper.javaBean2JsonString(logBean), e1);
    }

}
