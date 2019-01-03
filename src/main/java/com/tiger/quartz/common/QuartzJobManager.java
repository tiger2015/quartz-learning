package com.tiger.quartz.common;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzJobManager {
    private Scheduler scheduler = null;

    public QuartzJobManager() {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public void start() throws SchedulerException {
        if(scheduler == null){
            throw new NullPointerException("scheduler is null");
        }

        System.out.println("start scheduler");
        scheduler.start();
    }

    public void stop() throws SchedulerException {
        if(scheduler == null){
            throw new NullPointerException("scheduler is null");
        }
        scheduler.shutdown();
    }


    public void addJob(JobDescribe jobDescribe, Class clazz,Object executor, long interval) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity(jobDescribe.getJobKey())
                .build();

        jobDetail.getJobDataMap().put("data",executor);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobDescribe.getTriggerKey())
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInMilliseconds(interval))
                .build();
        scheduler.scheduleJob(jobDetail,trigger);
    }


    public void removeJob(JobDescribe jobDescribe) throws SchedulerException {
        synchronized (this){
            // 停止触发器
            scheduler.pauseTrigger(jobDescribe.getTriggerKey());
            // 移除触发器
            scheduler.unscheduleJob(jobDescribe.getTriggerKey());
            // 删除任务
            scheduler.deleteJob(jobDescribe.getJobKey());
        }
    }

}
