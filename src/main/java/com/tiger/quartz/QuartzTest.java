package com.tiger.quartz;

import com.tiger.quartz.common.JobDescribe;
import com.tiger.quartz.common.QuartzJobManager;
import com.tiger.quartz.job.CorsInfo;
import com.tiger.quartz.job.CorsInfoSyncJob;
import com.tiger.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {


    public static void main(String[] args) throws SchedulerException, InterruptedException {
//
//        try {
//            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//
//            // 任务
//            JobDetail job = JobBuilder
//                    .newJob(HelloJob.class)
//                    .withIdentity("hello-job", "group1")
//                    .usingJobData("count",2L)
//                    .build();
//            //job.getJobDataMap().put("count",2L);
//
//            Trigger trigger = TriggerBuilder
//                    .newTrigger()
//                    .withIdentity("hello-tigger", "group1")
//                    .startNow()
//                    .withSchedule(
//                            SimpleScheduleBuilder
//                                    .simpleSchedule()
//                                    .withIntervalInMilliseconds(1000)
//                                    .repeatForever())
//                    .build();
//
//            scheduler.start();
//            scheduler.scheduleJob(job,trigger);
//            //scheduler.shutdown();
//
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
        QuartzJobManager jobManager = new QuartzJobManager();
        jobManager.start();

        for(int i=0;i<300;i++){
            JobDescribe jobDescribe = new JobDescribe("job-"+i,"cors-group","trigger-"+i,"trigger-group");
            CorsInfo corsInfo = new CorsInfo();
            jobManager.addJob(jobDescribe, CorsInfoSyncJob.class,corsInfo,300);
        }

        Thread.sleep(5000);






    }
}
