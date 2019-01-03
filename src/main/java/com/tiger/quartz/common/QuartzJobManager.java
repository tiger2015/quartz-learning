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
        if(scheduler.isShutdown()){
            scheduler.start();
        }
    }

    public void stop() throws SchedulerException {
        if(scheduler == null){
            throw new NullPointerException("scheduler is null");
        }
        scheduler.shutdown();
    }


    public void addJob(JobDescribe jobDescribe,Class clazz,Object ...objects){
        JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobDescribe.getJobKey()).setJobData();

        JobDataMap dataMap = new JobDataMap();





    }


}
