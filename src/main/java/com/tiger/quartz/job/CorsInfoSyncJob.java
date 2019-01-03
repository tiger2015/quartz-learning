package com.tiger.quartz.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CorsInfoSyncJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        CorsInfo baselines = (CorsInfo) dataMap.get("data");
        // 进行基线信息同步
        baselines.syncBaselines();
        baselines.syncOrignalRtcm();
        // 基线解析


    }
}
