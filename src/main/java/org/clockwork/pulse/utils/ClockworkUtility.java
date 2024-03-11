package org.clockwork.pulse.utils;

import java.time.LocalDateTime;
import org.clockwork.pulse.dto.request.GetCallbackRequestDto;
import org.clockwork.pulse.dto.request.PostCallbackRequestDto;
import org.clockwork.pulse.entity.JobEntity;
import org.clockwork.pulse.models.RequestType;

public class ClockworkUtility {

  public static JobEntity getJobEntityFromPostDto(
      PostCallbackRequestDto requestDto, String jobId) {

    Long callBackTimeAfterMinutes = requestDto.getCallBackTimeAfterMinutes();
    LocalDateTime executionTime = LocalDateTime.now().plusMinutes(callBackTimeAfterMinutes);

    return JobEntity.builder()
        .jobId(jobId)
        .data(requestDto.getData())
        .url(requestDto.getUrl())
        .requestType(RequestType.POST)
        .executionTime(executionTime)
        .build();
  }

  public static JobEntity getJobEntityFromGetDto(
      GetCallbackRequestDto requestDto, String jobId) {

    Long callBackTimeAfterMinutes = requestDto.getCallBackTimeAfterMinutes();
    LocalDateTime executionTime = LocalDateTime.now().plusMinutes(callBackTimeAfterMinutes);

    return JobEntity.builder()
        .jobId(jobId)
        .data(null)
        .url(requestDto.getUrl())
        .requestType(RequestType.GET)
        .executionTime(executionTime)
        .build();
  }

}
