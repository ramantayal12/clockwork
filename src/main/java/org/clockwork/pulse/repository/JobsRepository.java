package org.clockwork.pulse.repository;

import java.util.List;
import org.clockwork.pulse.entity.JobEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends MongoRepository<JobEntity, String> {

  List<JobEntity> findByExecutionTimeBetween(Long startTime, Long endTime);

}
