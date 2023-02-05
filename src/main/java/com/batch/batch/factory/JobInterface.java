package com.batch.batch.factory;

import org.springframework.batch.core.Job;

public interface JobInterface<T> {

    String getJobName();
    Job returnObj ();

}
