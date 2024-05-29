package org.clockwork.pulse.service.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;
import org.clockwork.pulse.service.entity.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends CrudRepository<JobEntity, Long> {
  Optional<JobEntity> findByJobId(String jobId);

  Stream<JobEntity> streamByExecutionTimeBetween(LocalDateTime executionTimeStart,
      LocalDateTime executionTimeEnd);

  Page<JobEntity> findByExecutionTimeBetween(LocalDateTime executionTimeStart,
      LocalDateTime executionTimeEnd, Pageable pageable);
}
