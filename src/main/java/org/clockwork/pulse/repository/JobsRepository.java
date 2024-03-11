package org.clockwork.pulse.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.clockwork.pulse.entity.JobEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends CrudRepository<JobEntity, Long> {

  List<JobEntity> findByExecutionTimeBetween(Long startTime, Long endTime);

  Optional<JobEntity> findByJobId(String jobId);

  Stream<JobEntity> streamByExecutionTimeBetween(LocalDateTime executionTimeStart,
      LocalDateTime executionTimeEnd);

}
