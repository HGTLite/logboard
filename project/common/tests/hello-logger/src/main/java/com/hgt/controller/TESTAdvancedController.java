package com.hgt.controller;

import com.hgt.logger.StandardLogger;
import com.hgt.logger.formats.LogOptions;
import com.hgt.logger.formats.LogType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.hgt.ip.IPHelper.getRandomIp;

/******************************************************************************
 * Created by  Yao  on  2016/7/19
 * README:高级日志测试类
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
@RestController
public class TESTAdvancedController {

    private StandardLogger logger;

    public TESTAdvancedController() {
        logger = new StandardLogger(TESTAdvancedController.class, "hello002");
    }

    @RequestMapping("/v")
    public String defaultHome() {
        logger.logType(LogType.LOGIN);
        logger.options(LogOptions.USER_ID, "user001").options(LogOptions.USER_IP, getRandomIp());
        logger.i("LOG-AT-TESTAdvancedController. IP Address 001. INFO");
        return "LOG-AT-TESTAdvancedController. IP Address 001. INFO";
    }

    @RequestMapping(value = "/v/a", method = RequestMethod.GET)
    public String getA() {
        logger.logType(LogType.LOGIN);
        logger.options(LogOptions.USER_ID, "user002").options(LogOptions.USER_IP, getRandomIp());
        logger.i("LOG-AT-TESTAdvancedController. IP Address 002. INFO");
        return "LOG-AT-TESTAdvancedController. IP Address 002. INFO";
    }

    @RequestMapping("/v/b")
    public String getB() {
        logger.logType(LogType.LOGIN);
        logger.options(LogOptions.USER_ID, "user003").options(LogOptions.USER_IP, getRandomIp());
        logger.w("LOG-AT-TESTAdvancedController. IP Address 003. INFO");
        return "LOG-AT-TESTAdvancedController. IP Address 003. INFO";
    }

    @RequestMapping("/v/c")
    public String getC() {
        logger.logType(LogType.LOGIN);
        logger.options(LogOptions.USER_ID, "user004").options(LogOptions.USER_IP, getRandomIp());
        logger.e("LOG-AT-TESTAdvancedController. IP Address 004. INFO", new Exception());
        return "LOG-AT-TESTAdvancedController. IP Address 004. INFO";
    }


}
