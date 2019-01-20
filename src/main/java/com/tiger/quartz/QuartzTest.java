package com.tiger.quartz;

import com.tiger.quartz.common.JobDescribe;
import com.tiger.quartz.common.QuartzJobManager;
import com.tiger.quartz.testjob.CorsSubject;
import com.tiger.quartz.testjob.HandoutMessageJob;
import com.tiger.quartz.testjob.SyncBaselineJob;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class QuartzTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzTest.class);


    private static AtomicLong index = new AtomicLong(0);
    private static QuartzJobManager handoutMessageJobManager = new QuartzJobManager(2, "handout-message");
    private static QuartzJobManager syncBaselineJobManager = new QuartzJobManager(2, "sync-baseline");
    private static Map<Integer, CorsSubject> corsSubjectMap;

    static {
        corsSubjectMap = new ConcurrentHashMap<>();
        corsSubjectMap.put(1, new CorsSubject(1));
        corsSubjectMap.put(2, new CorsSubject(2));
    }


    public static void main(String[] args) throws SchedulerException, InterruptedException {
        handoutMessageJobManager.start();
        syncBaselineJobManager.start();
        corsSubjectMap.forEach((k, v) -> startSyncBaselineJob(v));

        new Thread(() -> {
            while (true) {
                corsSubjectMap.forEach((k, v) -> {
                    long id = index.getAndIncrement();
                    LOGGER.info("notify:" + k + ", message id:" + id);
                    v.noity(id);
                });
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "notice").start();
    }


    public static void startSyncBaselineJob(CorsSubject corsSubject) {
        JobDescribe jobDescribe = new JobDescribe("cors-" + corsSubject.getId(), "sync-baseline-job", "cors-" + corsSubject.getId(), "sync-baseline-tigger");
        try {
            syncBaselineJobManager.addScheduleJob(jobDescribe, SyncBaselineJob.class, corsSubject, 2000);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public static void addHandoutMessageJob(CorsSubject corsSubject, long id) {
        String idStr = "cors-" + corsSubject + "-" + id;
        JobDescribe jobDescribe = new JobDescribe(idStr, "handout-message-job", idStr, "handout-message-tigger");
        try {
            syncBaselineJobManager.addJob(jobDescribe, HandoutMessageJob.class, corsSubject);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

}
