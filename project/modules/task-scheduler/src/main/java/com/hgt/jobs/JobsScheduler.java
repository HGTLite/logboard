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

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail statsAppCountsJob = JobBuilder.newJob(StatsAppCountsJob.class).withIdentity("job1", "group1").build();
        JobDetail statsTypeCountsJob = JobBuilder.newJob(StatsTypeCountsJob.class).withIdentity("job1", "group2").build();
        JobDetail statsExpJob = JobBuilder.newJob(StatsExpCountsJob.class).withIdentity("job1", "group3").build();

        CronScheduleBuilder scheduleBuilder5s = CronScheduleBuilder.cronSchedule("0/5 * * * * ? *");
        CronScheduleBuilder scheduleBuilder10s = CronScheduleBuilder.cronSchedule("0/10 * * * * ? *");

        CronTrigger statsAppCountsTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(scheduleBuilder5s).build();
        CronTrigger statsTypeCountsTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2").withSchedule(scheduleBuilder10s).build();
        CronTrigger statsExpTrigger = TriggerBuilder.newTrigger().withIdentity("trigger3", "group3").withSchedule(scheduleBuilder5s).build();

        scheduler.scheduleJob(statsAppCountsJob, statsAppCountsTrigger);
        scheduler.scheduleJob(statsTypeCountsJob, statsTypeCountsTrigger);
        scheduler.scheduleJob(statsExpJob, statsExpTrigger);

    }

}
