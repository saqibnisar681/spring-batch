package com.batch.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Before job: " + jobExecution.getJobInstance().getJobName());
        System.out.println("Job Params: " + jobExecution.getJobParameters());
        System.out.println("Job Context: " + jobExecution.getExecutionContext());

        jobExecution.getExecutionContext().put("contxtKey","contxtValue");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("After job: " + jobExecution.getJobInstance().getJobName());
        System.out.println("Job Params: " + jobExecution.getJobParameters());
        System.out.println("Job Context: " + jobExecution.getExecutionContext());
    }
}
