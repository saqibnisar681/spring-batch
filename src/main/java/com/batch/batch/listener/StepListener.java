package com.batch.batch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class StepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Before step: " + stepExecution.getStepName());
        System.out.println(stepExecution.getExecutionContext());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("After step: " + stepExecution.getStepName());
        System.out.println(stepExecution.getExecutionContext());
        return ExitStatus.COMPLETED;
    }
}
