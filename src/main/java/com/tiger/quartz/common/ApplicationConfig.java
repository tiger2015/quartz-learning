package com.tiger.quartz.common;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class ApplicationConfig {
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Scheduler scheduler() throws SchedulerException {
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Properties properties = new Properties();
        properties.setProperty(StdSchedulerFactory.PROP_SCHED_INSTANCE_NAME, "test");
        properties.setProperty("org.quartz.threadPool.threadCount", "4");
        factory.initialize(properties);
        return factory.getScheduler();
    }


}
