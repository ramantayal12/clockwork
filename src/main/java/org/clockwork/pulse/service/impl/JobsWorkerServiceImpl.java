package org.clockwork.pulse.service.impl;

import static org.clockwork.pulse.utils.ClockworkUtility.getFetchJobDetailsResponseDto;
import static org.clockwork.pulse.utils.ClockworkUtility.getJobEntityFromGetDto;
import static org.clockwork.pulse.utils.ClockworkUtility.getJobEntityFromPostDto;

import lombok.SneakyThrows;
import org.clockwork.pulse.dao.JobsDaoLayer;
import org.clockwork.pulse.dto.request.FetchJobDetailsDto;
import org.clockwork.pulse.dto.request.GetCallbackRequestDto;
import org.clockwork.pulse.dto.request.PostCallbackRequestDto;
import org.clockwork.pulse.dto.response.FetchJobDetailsResponseDto;
import org.clockwork.pulse.dto.response.OnboardJobDetailsResponseDto;
import org.clockwork.pulse.models.JobStatus;
import org.clockwork.pulse.service.EventService;
import org.clockwork.pulse.service.JobsWorkerService;
import org.clockwork.pulse.service.util.IdGenerator;
import org.clockwork.pulse.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobsWorkerServiceImpl implements JobsWorkerService {

  private static final String JOB_ID_PREFIX = "J";
  private final IdGenerator idGenerator;
  private final JobsDaoLayer jobsDaoLayer;
  private final EventService eventService;

  @Autowired
  public JobsWorkerServiceImpl(IdGenerator idGenerator, JobsDaoLayer jobsDaoLayer,
      EventService eventService) {
    this.idGenerator = idGenerator;
    this.jobsDaoLayer = jobsDaoLayer;
    this.eventService = eventService;
  }

  @Override
  @SneakyThrows
  public OnboardJobDetailsResponseDto onboardJob(
      PostCallbackRequestDto requestDto) {

    // validate request
    ValidationUtil.validatePostCallbackRequest(requestDto);

    // create entity
    var generatedJobId = getGeneratedId();
    var jobEntity = getJobEntityFromPostDto(requestDto, generatedJobId);
    var jobId = jobsDaoLayer.saveEntity(jobEntity);

    eventService.sendEvent(jobId, JobStatus.CREATED);
    return OnboardJobDetailsResponseDto.builder()
        .jobId(jobId)
        .build();
  }

  @Override
  @SneakyThrows
  public OnboardJobDetailsResponseDto onboardJob(GetCallbackRequestDto requestDto) {

    // validate request
    ValidationUtil.validateGetCallbackRequest(requestDto);

    // create entity
    var generatedJobId = getGeneratedId();
    var jobEntity = getJobEntityFromGetDto(requestDto, generatedJobId);
    var jobId = jobsDaoLayer.saveEntity(jobEntity);

    eventService.sendEvent(jobId, JobStatus.CREATED);
    return OnboardJobDetailsResponseDto.builder()
        .jobId(jobId)
        .build();
  }

  public FetchJobDetailsResponseDto fetchJobDetails(FetchJobDetailsDto requestDto) {

    // validate request
    ValidationUtil.validateFetchRequest(requestDto);

    // fetch entity from db
    var jobEntity = jobsDaoLayer.getJobEntity(requestDto.getJobId());

    return getFetchJobDetailsResponseDto(jobEntity);

  }

  private String getGeneratedId() {
    return idGenerator.generateId(JOB_ID_PREFIX);
  }

}
