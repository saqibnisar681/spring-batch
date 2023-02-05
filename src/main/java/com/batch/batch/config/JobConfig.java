package com.batch.batch.config;

import com.batch.batch.listener.JobListener;
import com.batch.batch.listener.StepListener;
import com.batch.batch.processor.ItemProcessor;
import com.batch.batch.reader.ItemReader;
import com.batch.batch.service.Job.FirstJob;
import com.batch.batch.service.Task.TaskService;
import com.batch.batch.writer.ItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class JobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobListener jobListener;
    @Autowired
    private StepListener stepListener;
    @Autowired
    private ItemWriter itemWriter;
    @Autowired
    private ItemReader itemReader;
    @Autowired
    private ItemProcessor itemProcessor;
    @Autowired
    private TaskService taskService;
    @Autowired
    private FirstJob firstJob;

//    @Bean
    public Job firstJob(){

        /*return jobBuilderFactory.get("firstJob")
                .listener(jobListener)
                .incrementer(new RunIdIncrementer())
                .start(firstStep())
                .build();*/
        return firstJob.firstJob();
    }

//    @Bean
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

    protected Step firstStep(){

//        return stepBuilderFactory.get("firstStep").tasklet(firstTaskLet()).build();
        return stepBuilderFactory
                .get("firstStep")
                .tasklet(taskService)
                .listener(stepListener)
                .build();
    }
    private Tasklet firstTaskLet(){

        return (stepContribution, chunkContext) -> {
            System.out.println("first tasklet completed");
            return RepeatStatus.FINISHED;
        };
    }


}
