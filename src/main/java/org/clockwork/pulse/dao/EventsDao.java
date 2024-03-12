package org.clockwork.pulse.dao;

import org.clockwork.pulse.entity.EventEntity;
import org.clockwork.pulse.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventsDao {

  private final EventsRepository repository;

  @Autowired
  public EventsDao(EventsRepository repository) {
    this.repository = repository;
  }

  public void saveEvent(EventEntity entity){
    repository.save(entity);
  }

}
