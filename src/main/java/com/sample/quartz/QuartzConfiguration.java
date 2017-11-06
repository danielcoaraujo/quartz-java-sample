package com.sample.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Properties;

/**
 * Created by daniel on 06/11/17.
 */
@Configuration
public class QuartzConfiguration {

    @Autowired
    private ScheduledSample scheduledSample;

    @Value("${org.quartz.scheduler.instanceName}")
    private String instanceName;

    @Value("${org.quartz.scheduler.instanceId}")
    private String instanceId;

    @Value("${org.quartz.threadPool.threadCount}")
    private String threadCount;


    @Bean
    public org.quartz.spi.JobFactory jobFactory() {
        return new QuartzJobFactory();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        Properties quartzProperties = getProperties();
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(jobFactory());
        factory.setOverwriteExistingJobs(true);
        factory.setQuartzProperties(quartzProperties);
        factory.setTriggers(scheduledSample.cronTriggerFactoryBean().getObject());
        return factory;
    }

    private Properties getProperties() {
        Properties quartzProperties = new Properties();
        quartzProperties.setProperty("org.quartz.scheduler.instanceName",instanceName);
        quartzProperties.setProperty("org.quartz.scheduler.instanceId",instanceId);
        quartzProperties.setProperty("org.quartz.threadPool.threadCount",threadCount);
        return quartzProperties;
    }
}
