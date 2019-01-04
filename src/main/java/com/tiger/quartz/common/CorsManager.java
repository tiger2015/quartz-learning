package com.tiger.quartz.common;

import com.tiger.quartz.job.CorsInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CorsManager {
    private static final String GROUP_NAME = "cors";
    private static final String TRIGGER_GROUP_NAME="tigger";
    private static QuartzJobManager jobManager = new QuartzJobManager(10,"cors");
    private static Map<JobDescribe, CorsInfo> corsInfoMap =new ConcurrentHashMap<>();

    public void addCors(String id){




    }


    public void removeCors(String id){




    }

}
