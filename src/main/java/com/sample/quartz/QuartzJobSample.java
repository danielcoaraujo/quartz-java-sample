package com.sample.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by daniel on 06/11/17.
 */
public class QuartzJobSample extends QuartzJobBean {

    @Autowired
    private ServiceSample serviceSample;

    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        serviceSample.printAnything();
    }
}
