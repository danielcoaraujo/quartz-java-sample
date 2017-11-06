package com.sample.quartz;

import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

/**
 * Created by daniel on 06/11/17.
 */
@Configuration
public class ScheduledSample {

    @Value("${job.startDelay}")
    private Long startDelay;

    @Value("${job.description}")
    private String description;

    @Value("${job.key}")
    private String key;

    @Value("${job.cron}")
    private String cron;

    @Bean
    public CronTriggerFactoryBean exportFileCronTriggerFactoryBean() {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(exportFileJobDetails().getObject());
        factoryBean.setStartDelay(startDelay);
        factoryBean.setCronExpression(cron);
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        return factoryBean;
    }

    @Bean
    public JobDetailFactoryBean exportFileJobDetails() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(QuartzJobSample.class);
        jobDetailFactoryBean.setDescription(description);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setName(key);
        return jobDetailFactoryBean;
    }
}
