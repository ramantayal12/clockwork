package org.clockwork.pulse.utils;

import java.util.concurrent.TimeUnit;
import org.clockwork.pulse.dto.JobDto;
import org.clockwork.pulse.entity.JobEntity;

public class ClockworkUtility {

  public static JobEntity getJobEntityFromDto(JobDto jobDto, String jobId) {
    long millis = TimeUnit.MINUTES.toMillis(jobDto.getCallBackTimeAfterMinutes());
    return JobEntity.builder()
        .id(jobId)
        .data(jobDto.getData())
        .url(jobDto.getUrl())
        .requestType(jobDto.getRequestType())
        .executionTime(System.currentTimeMillis() + millis)
        .build();
  }

}
