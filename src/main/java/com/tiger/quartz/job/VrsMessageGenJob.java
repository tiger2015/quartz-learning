package com.tiger.quartz.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class VrsMessageGenJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        GridVrsInfo gridVrsInfo = (GridVrsInfo) dataMap.get("data");
        gridVrsInfo.genMessage();
    }
}
