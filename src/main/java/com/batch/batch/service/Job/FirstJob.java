package com.batch.batch.service.Job;

import com.batch.batch.factory.JobInterface;
import com.batch.batch.listener.JobListener;
import com.batch.batch.listener.StepListener;
import com.batch.batch.processor.ItemProcessor;
import com.batch.batch.reader.ItemReader;
import com.batch.batch.service.Task.TaskService;
import com.batch.batch.writer.ItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class FirstJob implements JobInterface<Job> {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobListener jobListener;
    @Autowired
    private StepListener stepListener;
    @Autowired
    private TaskService taskService;

    @Async
    public Job firstJob(){

        return jobBuilderFactory.get("firstJob")
                .listener(jobListener)
                .incrementer(new RunIdIncrementer())
                .start(firstStep())
                .build();
    }

    protected Step firstStep(){

        return stepBuilderFactory
                .get("firstStep")
                .tasklet(taskService)
                .listener(stepListener)
                .build();
    }

    @Override
    public String getJobName() {
        return firstJob().getName();
    }

    @Override
    public Job returnObj() {
        return firstJob();
    }
}
