package com.tiger.quartz.testjob;

import org.quartz.*;

public class SyncBaselineJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap dataMap = jobDetail.getJobDataMap();
        CorsSubject subject = (CorsSubject) dataMap.get("data");
        subject.syncBaseline();
    }
}
