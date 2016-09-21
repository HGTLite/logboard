package com.hgt.controller;

import com.hgt.logger.info.LogTags;
import com.hgt.logger.tools.StandardLogger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/******************************************************************************
 * Created by  Yao  on  2016/7/19
 * README:日志测试类
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
@RestController
public class HelloController {

    public StandardLogger logger;

    public HelloController() {
        logger = new StandardLogger(HelloController.class);
    }

    @RequestMapping("/")
    public String defaultHome() {
        logger.info("Request Contents is default index. DEBUG");
        return "Hello, it's me. DEBUG";
    }

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public String getA() {
        logger.logBean.setLogTags(LogTags.TEST);
        logger.info("Request Contents is A. INFO ");
        return "Hello, it's A. INFO";
    }

    @RequestMapping("/b")
    public String getB() {
        logger.logBean.setLogTags(LogTags.TEST);
        logger.warn("Request Contents is B. WARN ");
        return "Hello, it's B. WARN";
    }

    @RequestMapping("/c")
    public String getC() {
        logger.logBean.setLogTags(LogTags.GENERAL);
        logger.error("Request Contents is C. ERROR ", new Exception("!!!exception at getC!!!"));
        return "Hello, it's C. ERROR";
    }


}
