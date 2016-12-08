package com.hgt.jobs;

import com.hgt.ip.URLBuilder;
import com.hgt.utils.CacheThreadHelper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * README: 测试job类
 * Created by Yao on 12/7/16.
 * =============================================================================
 * CHANGELOG:
 */
public class StatsAppCountsJob implements Job {

    private static final String SCHEDULED_HOST = URLBuilder.buildHttpHostEndpoint("localhost", "8705");

    public void execute(JobExecutionContext context) {

        String targetURL1 = SCHEDULED_HOST + "/logb/stats/app/counts/5s";
        Map mapParams1 = new HashMap();
        CacheThreadHelper.newThreadGetByMap(targetURL1, mapParams1);


    }

}
