package com.tiger.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;


@Slf4j
public class JobManager {

    private static Scheduler scheduler;

    static {
        scheduler = MyApplicationContext.getBean(Scheduler.class);
    }

    public static void addJob(String jobId, Class jobClass, long intervalMills) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(jobId)
                .build();
        Trigger trigger =
                TriggerBuilder.newTrigger()
                        .startNow()
                        .withIdentity(jobId)
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                .repeatForever()
                                .withIntervalInMilliseconds(intervalMills))
                        .build();
        scheduler.scheduleJob(jobDetail, trigger);
        log.info("start job:" + jobId);
    }

    public static void stopJob(String jobId) throws SchedulerException {
        scheduler.deleteJob(JobKey.jobKey(jobId));
    }

    public static void interruptJob(String jobId) throws UnableToInterruptJobException {
        scheduler.interrupt(JobKey.jobKey(jobId));
    }
}
