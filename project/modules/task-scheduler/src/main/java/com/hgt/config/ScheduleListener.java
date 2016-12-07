package com.hgt.config;

import com.hgt.jobs.JobsScheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * README:
 * Created by Yao on 12/7/16.
 * =============================================================================
 * CHANGELOG:
 */
@Configuration
public class ScheduleListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    public JobsScheduler myScheduler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        System.out.println("定时任务监听器，开始定时任务的调度");

        try {
            myScheduler.scheduleJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
