package com.hgt.controller;

import com.hgt.logger.BasicLogger;
import com.hgt.logger.formats.LogType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/******************************************************************************
 * Created by  Yao  on  2016/7/19
 * README:基础日志测试类
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
@RestController
public class TESTBasicController {

    private BasicLogger logger;

    public TESTBasicController() {
        logger = new BasicLogger(TESTBasicController.class, "hello1");
    }

    @RequestMapping("/")
    public String defaultHome() {
        logger.logType(LogType.TEST);
        logger.i("Request Contents is default index. Hello, it's me. DEBUG");
        return "Hello, it's me. DEBUG";
    }

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public String getA() {
        logger.logType(LogType.GENERAL);
        logger.i("Request Contents is A. Hello, it's A. INFO ");
        return "Hello, it's A. INFO";
    }

    @RequestMapping("/b")
    public String getB() {
        logger.logType(LogType.LOGIN);
        logger.w("Request Contents is B. Hello, it's B. WARN ");
        return "Hello, it's B. WARN";
    }

    @RequestMapping("/c")
    public String getC() {
        logger.logType(LogType.QUERY);
        logger.e("Request Contents is C. Hello, it's C. ERROR ", new Exception("==========exception at getC!!!=========="));
        return "Hello, it's C. ERROR";
    }

}
