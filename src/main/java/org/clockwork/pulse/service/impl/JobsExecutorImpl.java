package org.clockwork.pulse.service.impl;

import org.clockwork.pulse.client.CallbackClient;
import org.clockwork.pulse.dao.JobsDaoLayer;
import org.clockwork.pulse.service.JobsExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JobsExecutorImpl implements JobsExecutor {

  private final JobsDaoLayer jobsDaoLayer;
  private final CallbackClient callbackClient;

  @Autowired
  public JobsExecutorImpl(JobsDaoLayer jobsDaoLayer, CallbackClient callbackClient) {
    this.jobsDaoLayer = jobsDaoLayer;
    this.callbackClient = callbackClient;
  }

  @Override
  public String executeJob(String jobId) {

    var jobEntity = jobsDaoLayer.getJobEntity(jobId);

    return switch (jobEntity.getRequestType()) {
      case GET -> callbackClient.executeGetMethod(jobEntity.getUrl());
      case POST -> callbackClient.executePostMethod(jobEntity.getUrl(), jobEntity.getData());
    };

  }


}
