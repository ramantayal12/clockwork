package org.clockwork.pulse.dao;

import java.util.List;
import org.clockwork.pulse.entity.JobEntity;

public interface JobsDaoLayer {

  String saveEntity(JobEntity jobEntity);

  JobEntity getJobEntity(String jobId);

  List<JobEntity> getBatchOfJobsBetweenTimestamps(Long startTime, Long endTime);

}
