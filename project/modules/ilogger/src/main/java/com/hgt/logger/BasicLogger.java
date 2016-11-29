package com.hgt.logger;

import com.hgt.logger.formats.LogKeyInfo;
import com.hgt.logger.formats.LogLevels;
import com.hgt.logger.formats.LogType;
import com.hgt.logger.validator.LoggerInputsValidator;
import com.hgt.utils.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

/******************************************************************************
 * Created by  Yao  on  2016/7/4
 * README:基本日志打印工具类
 *        只支持设置AppCode, LogType, LogMsg
 *        不支持设置可选字段
 * TO—DO: 日志初始化时参数验证失败仍可打日志，改为不能打
 * ============================================================================
 * CHANGELOG：160927重构
 ******************************************************************************/
@SuppressWarnings("unused")
public class BasicLogger extends ILogger {

    //日志必需内容
    private LogKeyInfo info;
    //实际日志打印对象
    private Logger logger;
    //javabean转json对象
    private JsonHelper jsonHelper;

    //log输入信息验证
    private ArrayList<String> infoList;
    private LoggerInputsValidator logValidator;

    /**
     * 指定日志类型为GENERAL
     *
     * @param c
     * @param strAppCode
     */
    public BasicLogger(Class c, String strAppCode) {

        logger = LoggerFactory.getLogger(c);
        super.LEVEL=0;

        infoList = new ArrayList<>();
        infoList.add(strAppCode);
        logValidator = new LoggerInputsValidator(infoList);
        if (logValidator.isLogValid()) {
            info = new LogKeyInfo(strAppCode, "General");
            info.setLogOptions(null);
            jsonHelper = new JsonHelper();
        } else {
            logger.error("==========BaiscLogger初始化失败!!!!!==========");
        }
    }


    /**
     * 指定日志类别，输入值参考LogTags枚举
     *
     * @param c
     * @param strAppCode
     * @param type
     */
    public BasicLogger(Class c, String strAppCode, String type) {
        logger = LoggerFactory.getLogger(c);
        super.LEVEL=0;

        infoList = new ArrayList<>();
        infoList.add(strAppCode);
        logValidator = new LoggerInputsValidator(infoList);

        if (logValidator.isLogValid()) {
            info = new LogKeyInfo(strAppCode, type);
            info.setLogOptions(null);
            jsonHelper = new JsonHelper();
        } else {
            logger.error("==========BaiscLogger初始化失败!!!!!==========");
        }
    }

    /**
     * 指定日志类别和日志内容
     *
     * @param c
     * @param strAppCode
     * @param type
     * @param msg
     */
    public BasicLogger(Class c, String strAppCode, String type, String msg) {
        logger = LoggerFactory.getLogger(c);
        super.LEVEL=0;

        infoList = new ArrayList<>();
        infoList.add(strAppCode);
        logValidator = new LoggerInputsValidator(infoList);

        if (logValidator.isLogValid()) {
            info = new LogKeyInfo(strAppCode, type, msg);
            info.setLogOptions(null);
            jsonHelper = new JsonHelper();
        } else {
            logger.error("==========BaiscLogger初始化失败!!!!!==========");
        }

    }

    public BasicLogger logLevel(LogLevels strLevel){
        super.setLEVEL(strLevel.ordinal());
        return this;
    }

    public BasicLogger appCode(String strCode){
        info.setAppCode(strCode);
        return this;
    }

    public BasicLogger logType(LogType strType){
        info.setLogType(strType);
        return this;
    }

    public BasicLogger logMsg(String strMsg){
        info.setLogMsg(strMsg);
        return this;
    }

    public BasicLogger logOptions(HashMap<String, String> o){
        info.setLogOptions(o);
        return this;
    }


    /**
     * 打印debug型日志
     *
     * @param strD
     */
    @Override
    public void d(String strD) {
        info.setLogMsg(strD);
        if (super.LEVEL  <= DEBUG) {
            logger.debug(jsonHelper.convertBean2Json(info));
        }
    }

    /**
     * 打印info型日志
     *
     * @param strI
     */
    @Override
    public void i(String strI) {
        info.setLogMsg(strI);

        if (super.LEVEL  <= INFO) {
            logger.info(jsonHelper.convertBean2Json(info));
        }
    }

    /**
     * 打印warn型日志
     *
     * @param strW
     */
    @Override
    public void w(String strW) {
        info.setLogMsg(strW);
        if (super.LEVEL  <= WARN) {
            logger.warn(jsonHelper.convertBean2Json(info));
        }
    }

    /**
     * 打印error型日志
     *
     * @param strE
     * @param e
     */
    @Override
    public void e(String strE, Exception e) {
        info.setLogMsg(strE);
        if (super.LEVEL <= ERROR) {
            logger.error(jsonHelper.convertBean2Json(info), e);
        }
    }

}
