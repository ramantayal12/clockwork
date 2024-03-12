package org.clockwork.pulse.service;

import org.clockwork.pulse.models.JobStatus;

public interface EventService {

  void sendEvent(String jobId, JobStatus status);

}
