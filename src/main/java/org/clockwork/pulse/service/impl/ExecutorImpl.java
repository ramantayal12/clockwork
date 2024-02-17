package org.clockwork.pulse.service.impl;

import org.clockwork.pulse.client.ExecutorClient;
import org.clockwork.pulse.dao.JobsDaoLayer;
import org.clockwork.pulse.service.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExecutorImpl implements Executor {

  private final JobsDaoLayer jobsDaoLayer;
  private final ExecutorClient executorClient;

  @Value(value = "${clockwork.repetition-time-unit}")
  private Long repetitionTime;

  @Autowired
  public ExecutorImpl(JobsDaoLayer jobsDaoLayer, ExecutorClient executorClient) {
    this.jobsDaoLayer = jobsDaoLayer;
    this.executorClient = executorClient;
  }

  @Override
  public String executeJob(String jobId) {

    var jobEntity = jobsDaoLayer.getJobEntity(jobId);

    return switch (jobEntity.getRequestType()) {
      case GET -> executorClient.executeGetMethod(jobEntity.getUrl());
      case POST -> executorClient.executePostMethod(jobEntity.getUrl(), jobEntity.getData());
    };

  }


}
