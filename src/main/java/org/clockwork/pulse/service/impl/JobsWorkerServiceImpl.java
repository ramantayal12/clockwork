package org.clockwork.pulse.service.impl;

import static org.clockwork.pulse.utils.ClockworkUtility.getJobEntityFromGetDto;
import static org.clockwork.pulse.utils.ClockworkUtility.getJobEntityFromPostDto;

import lombok.SneakyThrows;
import org.clockwork.pulse.dao.JobsDaoLayer;
import org.clockwork.pulse.dto.request.GetCallbackRequestDto;
import org.clockwork.pulse.dto.request.PostCallbackRequestDto;
import org.clockwork.pulse.dto.response.FetchJobDetailsResponseDto;
import org.clockwork.pulse.dto.response.OnboardJobDetailsResponseDto;
import org.clockwork.pulse.entity.JobEntity;
import org.clockwork.pulse.service.ValidationService;
import org.clockwork.pulse.service.JobsWorkerService;
import org.clockwork.pulse.service.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobsWorkerServiceImpl implements JobsWorkerService {

  private static final String JOB_ID_PREFIX = "J";
  private final IdGenerator idGenerator;
  private final JobsDaoLayer jobsDaoLayer;
  private final ValidationService validationService;

  @Autowired
  public JobsWorkerServiceImpl(IdGenerator idGenerator, JobsDaoLayer jobsDaoLayer,
      ValidationService validationService) {
    this.idGenerator = idGenerator;
    this.jobsDaoLayer = jobsDaoLayer;
    this.validationService = validationService;
  }

  @Override
  @SneakyThrows
  public OnboardJobDetailsResponseDto onboardJob(
      PostCallbackRequestDto requestDto){

    // validate request
    validationService.validatePostCallbackRequest(requestDto);

    // create entity
    var generatedJobId = getGeneratedId();
    var jobEntity = getJobEntityFromPostDto(requestDto, generatedJobId);
    var jobId = jobsDaoLayer.saveEntity(jobEntity);
    return OnboardJobDetailsResponseDto.builder()
        .jobId(jobId)
        .build();
  }

  @Override
  @SneakyThrows
  public OnboardJobDetailsResponseDto onboardJob(GetCallbackRequestDto requestDto) {

    // validate request
    validationService.validateGetCallbackRequest(requestDto);

    // create entity
    var generatedJobId = getGeneratedId();
    var jobEntity = getJobEntityFromGetDto(requestDto, generatedJobId);
    var jobId = jobsDaoLayer.saveEntity(jobEntity);
    return OnboardJobDetailsResponseDto.builder()
        .jobId(jobId)
        .build();
  }

  public FetchJobDetailsResponseDto fetchJobDetails(String jobId){

    JobEntity jobEntity = jobsDaoLayer.getJobEntity(jobId);

    return FetchJobDetailsResponseDto.builder()
        .url(jobEntity.getUrl())
        .requestType(jobEntity.getRequestType())
        .data(jobEntity.getData())
        .executionTime(jobEntity.getExecutionTime())
        .build();

  }

  private String getGeneratedId() {
    return idGenerator.generateId(JOB_ID_PREFIX);
  }

}