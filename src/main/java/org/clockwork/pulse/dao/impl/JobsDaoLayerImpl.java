package org.clockwork.pulse.dao.impl;

import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.clockwork.pulse.dao.JobsDaoLayer;
import org.clockwork.pulse.entity.JobEntity;
import org.clockwork.pulse.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    return resultEntity.getJobId();
  }

  @Override
  public JobEntity getJobEntity(String jobId) {
    var resultEntity = repository.findByJobId(jobId);
    return resultEntity.orElse(null);
  }

  @Override
  public Stream<JobEntity> streamBatchOfJobsBetweenTimestamps(LocalDateTime startTime,
      LocalDateTime endTime) {
    return repository.streamByExecutionTimeBetween(startTime, endTime);
  }

  @Override
  public Page<JobEntity> pageBatchOfJobsBetweenTimestamps(LocalDateTime startTime,
      LocalDateTime endTime, Pageable pageable) {
    return repository.pageByExecutionTimeBetween(startTime, endTime, pageable);
  }


}
