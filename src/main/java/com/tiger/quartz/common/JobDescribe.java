package com.tiger.quartz.common;

import org.quartz.JobKey;
import org.quartz.TriggerKey;

import java.util.Objects;

public class JobDescribe {
    private JobKey jobKey;
    private TriggerKey triggerKey;

    public JobDescribe(String jobName, String jobGroupName, String triggerName, String triggerGroupName){
        this.jobKey = JobKey.jobKey(jobName,jobGroupName);
        this.triggerKey = TriggerKey.triggerKey(triggerName,triggerGroupName);
    }

    public JobKey getJobKey() {
        return jobKey;
    }

    public TriggerKey getTriggerKey() {
        return triggerKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobDescribe that = (JobDescribe) o;
        return Objects.equals(jobKey, that.jobKey) &&
                Objects.equals(triggerKey, that.triggerKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobKey, triggerKey);
    }
}
