package org.clockwork.pulse.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import org.clockwork.pulse.entity.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobsDaoLayer {

  String saveEntity(JobEntity jobEntity);

  JobEntity getJobEntity(String jobId);

  /**
   * Stream return has con, that it keeps connection with dB open
   */
  Stream<JobEntity> streamBatchOfJobsBetweenTimestamps(LocalDateTime startTime,
      LocalDateTime endTime);

  Page<JobEntity> pageBatchOfJobsBetweenTimestamps(LocalDateTime startTime,
      LocalDateTime endTime, Pageable pageable);

}
