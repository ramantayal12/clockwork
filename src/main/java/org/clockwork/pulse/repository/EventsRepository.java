package org.clockwork.pulse.repository;

import java.util.Optional;
import org.clockwork.pulse.entity.EventEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends CrudRepository<EventEntity, Long> {

  Optional<EventEntity> findByEventId(String eventId);

  Optional<EventEntity> findByWorkflowId(String workflowId);

  Optional<EventEntity> findByJobId(String jobId);

}
