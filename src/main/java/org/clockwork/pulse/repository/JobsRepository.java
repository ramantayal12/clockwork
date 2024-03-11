package org.clockwork.pulse.repository;

import java.util.List;
import java.util.Optional;
import org.clockwork.pulse.entity.JobEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends CrudRepository<JobEntity, Long> {

  List<JobEntity> findByExecutionTimeBetween(Long startTime, Long endTime);

  Optional<JobEntity> findByJobId(String jobId);

}
