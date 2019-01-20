package com.tiger.quartz.testjob;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class HandoutMessageJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(HandoutMessageJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap dataMap = jobDetail.getJobDataMap();
        CorsSubject subject = (CorsSubject) dataMap.get("data");
        subject.handoutMessage();
    }
}
