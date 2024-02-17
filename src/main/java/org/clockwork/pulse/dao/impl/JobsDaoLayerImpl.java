package org.clockwork.pulse.dao.impl;

import java.util.List;
import org.clockwork.pulse.dao.JobsDaoLayer;
import org.clockwork.pulse.entity.JobEntity;
import org.clockwork.pulse.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobsDaoLayerImpl implements JobsDaoLayer {

  private final JobsRepository repository;

  @Autowired
  public JobsDaoLayerImpl(JobsRepository jobsRepository) {
    this.repository = jobsRepository;
  }

  @Override
  public String saveEntity(JobEntity jobEntity) {
    var resultEntity = repository.save(jobEntity);
    return resultEntity.getId();
  }

  @Override
  public JobEntity getJobEntity(String jobId) {
    var resultEntity = repository.findById(jobId);
    return resultEntity.orElse(null);
  }

  @Override
  public List<JobEntity> getBatchOfJobsBetweenTimestamps(Long startTime, Long endTime) {
    return repository.findByExecutionTimeBetween(startTime, endTime);
  }
}
