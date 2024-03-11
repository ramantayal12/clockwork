package org.clockwork.pulse.utils;

import java.util.concurrent.TimeUnit;
import org.clockwork.pulse.dto.request.SaveJobRequestDto;
import org.clockwork.pulse.entity.JobEntity;

public class ClockworkUtility {

  public static JobEntity getJobEntityFromDto(SaveJobRequestDto saveJobRequestDto, String jobId) {

    long millis = TimeUnit.MINUTES.toMillis(saveJobRequestDto.getCallBackTimeAfterMinutes());

    return JobEntity.builder()
        .jobId(jobId)
        .data(saveJobRequestDto.getData())
        .url(saveJobRequestDto.getUrl())
        .requestType(saveJobRequestDto.getRequestType())
        .executionTime(System.currentTimeMillis() + millis)
        .build();
  }

}
