package com.batch.batch.service.Job;

import com.batch.batch.factory.JobInterface;
import com.batch.batch.processor.ItemProcessor;
import com.batch.batch.reader.ItemReader;
import com.batch.batch.writer.ItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SecondJob implements JobInterface<Job> {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private ItemWriter itemWriter;
    @Autowired
    private ItemReader itemReader;
    @Autowired
    private ItemProcessor itemProcessor;

    @Async
    public Job chunkjob(){

        return jobBuilderFactory
                .get("chunkJob")
                .incrementer(new RunIdIncrementer())
                .start(chunkStep())
                .build();
    }

    protected Step chunkStep(){

        return stepBuilderFactory
                .get("chunkStep")
                .<Long, Long>chunk(3)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Override
    public String getJobName() {
        return chunkjob().getName();
    }

    @Override
    public Job returnObj() {
        return chunkjob();
    }
}
