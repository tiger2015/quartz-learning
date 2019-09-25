package com.tiger.quartz.job;

import com.tiger.quartz.JobManager;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.concurrent.TimeUnit;

@Slf4j
public class MyInterruptedJob implements InterruptableJob {
    private JobKey jobKey;
    @Override
    public void interrupt() throws UnableToInterruptJobException {
        log.info("interrupted, remove job");
        try {
            JobManager.stopJob(jobKey.getName());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("execute interruptable job");
        jobKey = context.getJobDetail().getKey();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
