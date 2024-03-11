package org.clockwork.pulse.service.impl;

import org.clockwork.pulse.client.CallbackExecutor;
import org.clockwork.pulse.dao.JobsDaoLayer;
import org.clockwork.pulse.service.JobsExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JobsExecutorImpl implements JobsExecutor {

  private final JobsDaoLayer jobsDaoLayer;
  private final CallbackExecutor callbackExecutor;

  @Value(value = "${clockwork.repetition-time-unit}")
  private Long repetitionTime;

  @Autowired
  public JobsExecutorImpl(JobsDaoLayer jobsDaoLayer, CallbackExecutor callbackExecutor) {
    this.jobsDaoLayer = jobsDaoLayer;
    this.callbackExecutor = callbackExecutor;
  }

  @Override
  public String executeJob(String jobId) {

    var jobEntity = jobsDaoLayer.getJobEntity(jobId);

    return switch (jobEntity.getRequestType()) {
      case GET -> callbackExecutor.executeGetMethod(jobEntity.getUrl());
      case POST -> callbackExecutor.executePostMethod(jobEntity.getUrl(), jobEntity.getData());
    };

  }


}
