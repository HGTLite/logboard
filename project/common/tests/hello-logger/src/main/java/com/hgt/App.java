package com.hgt;

import com.hgt.logger.StandardLogger;
import com.hgt.logger.formats.LogOptions;
import com.hgt.logger.formats.LogType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.hgt.ip.IPHelper.getRandomIp;


@SpringBootApplication
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            //日志条数
            int ii = 0;

            @Override
            public void run() {
                StandardLogger logger = new StandardLogger(App.class, "hello1");
                StandardLogger log = (StandardLogger) logger.logType(LogType.LOGIN);
                log.options(LogOptions.USER_ID, "user001").options(LogOptions.USER_IP, getRandomIp());
                if (ii % 5 == 0) {
                    log.i("delay 5 seconds 首次延迟5s执行, and excute every 4 seconds 每次间隔4s执行");
                    ii++;
                } else {
                    log.w("这是第 " + ii + " 条消息！！！");
                    ii++;
                }
            }
        }, 5, 4, TimeUnit.SECONDS);
    }

}