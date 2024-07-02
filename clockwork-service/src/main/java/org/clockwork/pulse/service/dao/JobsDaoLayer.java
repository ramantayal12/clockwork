package org.clockwork.pulse.service.dao;

import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.clockwork.pulse.service.entity.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobsDaoLayer {

  String saveEntity(JobEntity jobEntity);

  JobEntity getJobEntity(String jobId);

  Stream<JobEntity> streamBatchOfJobsBetweenTimestamps(LocalDateTime startTime,
      LocalDateTime endTime);

  Page<JobEntity> pageBatchOfJobsBetweenTimestamps(LocalDateTime startTime,
      LocalDateTime endTime, Pageable pageable);

}
