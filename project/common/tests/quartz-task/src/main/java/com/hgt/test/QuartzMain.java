package com.hgt.test;

import java.text.ParseException;

import com.hgt.jobs.What21Job;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzMain {

    public static void main(String[] args) throws SchedulerException,
            ParseException {
        // 任务名称
        String jobName = "what21.com";
        // 任务组
        String jobGroup = "what21.com";

        // 构建JobDetail
        JobDetail jobDetail = JobBuilder.newJob(What21Job.class)
                .withIdentity(jobName, jobGroup)
                .build();

        // 触发名称
        String triName = "what21.com";
        // 出发表达式
        String triPress = "*/10 * * * * ?";
        CronExpression express = new CronExpression(triPress);
        // 构建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triName, jobGroup)
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(express))
                .build();

        // 创建调度器（Scheduler）
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        // 注册调度器（Scheduler）
        sched.scheduleJob(jobDetail, trigger);

        // 启动调度器（Scheduler）
        sched.start();

    }

}