package com.tiger.quartz;

import com.tiger.quartz.job.MyInterruptedJob;
import com.tiger.quartz.job.MyJob;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobManager.addJob("test-1", MyJob.class, 3000);
        JobManager.addJob("test-2", MyInterruptedJob.class, 1000);
        TimeUnit.SECONDS.sleep(8);
        JobManager.interruptJob("test-2");
    }
}
