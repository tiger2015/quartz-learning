package com.tiger.quartz.common;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class QuartzJobManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzJobManager.class);

    private Scheduler scheduler = null;

    public QuartzJobManager(int maxThreads, String name) {
        try {





            //DirectSchedulerFactory factory = DirectSchedulerFactory.getInstance();
            //factory.createVolatileScheduler(maxThreads);
            //scheduler = factory.getScheduler();

            StdSchedulerFactory factory = new StdSchedulerFactory();
            Properties props = new Properties();
            props.setProperty("org.quartz.threadPool.threadCount", maxThreads + "");
            props.setProperty("org.quartz.scheduler.instanceName", name);
            factory.initialize(props);
            scheduler = factory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public void start() throws SchedulerException {
        if (scheduler == null) {
            throw new NullPointerException("scheduler is null");
        }

        scheduler.start();
    }

    public void stop() throws SchedulerException {
        if (scheduler == null) {
            throw new NullPointerException("scheduler is null");
        }
        scheduler.shutdown();
    }


    public void addScheduleJob(JobDescribe jobDescribe, Class clazz, Object executor, long interval) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity(jobDescribe.getJobKey())
                .build();

        jobDetail.getJobDataMap().put("data", executor);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobDescribe.getTriggerKey())
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInMilliseconds(interval))
                .build();
        synchronized (this){
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }


    public void addJob(JobDescribe jobDescribe, Class clazz, Object executor) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity(jobDescribe.getJobKey())
                .build();

        jobDetail.getJobDataMap().put("data", executor);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobDescribe.getTriggerKey())
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(0))
                .build();

        synchronized (this){
            scheduler.scheduleJob(jobDetail,trigger);
        }
    }

    public void removeJob(JobDescribe jobDescribe) throws SchedulerException {
        synchronized (this) {
            // 停止触发器
            scheduler.pauseTrigger(jobDescribe.getTriggerKey());
            // 移除触发器
            scheduler.unscheduleJob(jobDescribe.getTriggerKey());
            // 删除任务
            scheduler.deleteJob(jobDescribe.getJobKey());
        }
    }

}
