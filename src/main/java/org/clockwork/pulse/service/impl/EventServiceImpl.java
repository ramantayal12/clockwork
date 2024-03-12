package org.clockwork.pulse.service.impl;

import static org.clockwork.pulse.contansts.Constants.EVENT_PREFIX;

import org.clockwork.pulse.dao.EventsDao;
import org.clockwork.pulse.entity.EventEntity;
import org.clockwork.pulse.models.JobStatus;
import org.clockwork.pulse.service.EventService;
import org.clockwork.pulse.service.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

  private final IdGenerator idGenerator;
  private final EventsDao eventsDao;

  @Autowired
  public EventServiceImpl(IdGenerator idGenerator, EventsDao eventsDao) {
    this.idGenerator = idGenerator;
    this.eventsDao = eventsDao;
  }

  public void sendEvent(String jobId, JobStatus status){

    EventEntity entity = getEventEntity(jobId, status);
    eventsDao.saveEvent(entity);

  }

  private EventEntity getEventEntity(String jobId, JobStatus status) {
    return EventEntity.builder()
        .eventId(idGenerator.generateId(EVENT_PREFIX))
        .jobId(jobId)
        .jobStatus(status)
        .build();
  }


}
