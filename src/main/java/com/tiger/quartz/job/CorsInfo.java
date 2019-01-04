package com.tiger.quartz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class CorsInfo {
    private static final Logger LOGGER = LoggerFactory.getLogger(CorsInfo.class);
    private String id; // 站点标识
    private List<String> relaCors; //关联参考站
    private long latestSyncTime; // 最新同步时间
    private Map<String,String> rtcmMap; //最新的观测电文

    // 根据VRS的位置获取最新的VRS观测数据
    public void getVrsObsData(){

    }

    // 基线数据同步
    public void syncBaselines(){

        LOGGER.info("同步基线");


    }


    public void syncOrignalRtcm(){
        LOGGER.info("同步电文");

    }

}
