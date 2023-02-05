package com.batch.batch.service.Task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
        System.out.println("Injecting external task");
        System.out.println(chunkContext.getStepContext().getJobExecutionContext());
        System.out.println(chunkContext.getStepContext().getStepExecutionContext());
        return RepeatStatus.FINISHED;
    }
}
