package com.batch.batch.factory;

import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JobFactory {

    private static Map<String, JobInterface<Job>> jobMap;

    @Autowired
    private void ViewerFactory(List<JobInterface<Job>> jobs) {
        jobMap = jobs.stream().collect(Collectors.toUnmodifiableMap(JobInterface<Job>::getJobName, Function.identity()));
    }

    public static JobInterface<Job> getJobProcess(String jobName) {
        return Optional.ofNullable(jobMap.get(jobName)).orElseThrow(IllegalArgumentException::new);
    }
}
