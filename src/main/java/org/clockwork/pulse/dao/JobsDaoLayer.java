package org.clockwork.pulse.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import org.clockwork.pulse.entity.JobEntity;

public interface JobsDaoLayer {

  String saveEntity(JobEntity jobEntity);

  JobEntity getJobEntity(String jobId);

  List<JobEntity> getBatchOfJobsBetweenTimestamps(Long startTime, Long endTime);

  Stream<JobEntity> streamBatchOfJobsBetweenTimestamps(LocalDateTime startTime,
      LocalDateTime endTime);

}
