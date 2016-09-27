package com.hgt.logger.tools;

import com.hgt.logger.formats.LogKeyInfo;
import com.hgt.utils.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/******************************************************************************
 * Created by  Yao  on  2016/7/4
 * README:基本日志打印工具类
 * 只支持设置AppCode, LogType, LogMsg
 * 不支持设置可选字段
 * ============================================================================
 * CHANGELOG：160927重构
 ******************************************************************************/
@SuppressWarnings("unused")
public class BasicLogger implements ILogger {

    //日志必需内容
    protected LogKeyInfo logKeyInfo;
    //实际日志打印对象
    protected Logger logger;
    //javabean转json对象
    protected JsonHelper jsonHelper;

    //产生GENERAL型日志
    public BasicLogger(Class c, String strAppCode) {
        logKeyInfo = new LogKeyInfo(strAppCode, "General");
        logKeyInfo.setLogOptions(null);
        jsonHelper = new JsonHelper();
        logger = LoggerFactory.getLogger(c);
    }

    //指定日志类别，输入值参考LogTags枚举
    public BasicLogger(Class c, String strAppCode, String type) {
        logKeyInfo = new LogKeyInfo(strAppCode, type);
        logKeyInfo.setLogOptions(null);
        jsonHelper = new JsonHelper();
        logger = LoggerFactory.getLogger(c);
    }

    //指定日志类别和日志内容
    public BasicLogger(Class c, String strAppCode, String type, String msg) {
        logKeyInfo = new LogKeyInfo(strAppCode, type, msg);
        logKeyInfo.setLogOptions(null);
        jsonHelper = new JsonHelper();
        logger = LoggerFactory.getLogger(c);
    }


    @Override
    public void d(String strD) {
        logKeyInfo.setLogMsg(strD);
        if (LEVEL <= DEBUG) {
            logger.debug(jsonHelper.convertBean2Json(logKeyInfo));
        }
    }

    @Override
    public void i(String strI) {
        logKeyInfo.setLogMsg(strI);
        if (LEVEL <= INFO) {
            logger.info(jsonHelper.convertBean2Json(logKeyInfo));
        }
    }

    @Override
    public void w(String strW) {
        logKeyInfo.setLogMsg(strW);
        if (LEVEL <= WARN) {
            logger.warn(jsonHelper.convertBean2Json(logKeyInfo));
        }
    }

    @Override
    public void e(String strE, Exception e) {
        logKeyInfo.setLogMsg(strE);
        if (LEVEL <= ERROR) {
            logger.error(jsonHelper.convertBean2Json(logKeyInfo), e);
        }
    }


}
