package com.batch.batch.controller;

import com.batch.batch.factory.JobFactory;
import com.batch.batch.factory.JobInterface;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/job")
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

    @GetMapping("{jobId}/start")
    public boolean startJob(@PathVariable String jobId) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        JobInterface<Job> job = JobFactory.getJobProcess(jobId);
        jobLauncher.run(job.returnObj(), new JobParameters());
        return true;
    }
}
