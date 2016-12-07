package com.hgt.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JobImpl implements Job {
    public void execute(JobExecutionContext context) {
        System.out.println("时间是：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}