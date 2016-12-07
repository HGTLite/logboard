package com.hgt.jobs;

import org.quartz.*;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

/**
 * README:
 * Created by Yao on 12/7/16.
 * =============================================================================
 * CHANGELOG:
 */
@Service
public class JobsScheduler {

    @Autowired
     SchedulerFactoryBean schedulerFactoryBean;

    public void scheduleJob() throws SchedulerException {

        Scheduler scheduler =schedulerFactoryBean.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(StatsAppCounts5sJob.class).withIdentity("job1","group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ? *");

       CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1").withSchedule(scheduleBuilder).build();

        scheduler.scheduleJob(jobDetail,trigger);

    }

}
