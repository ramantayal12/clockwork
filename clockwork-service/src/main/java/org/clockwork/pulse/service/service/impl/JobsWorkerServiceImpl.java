package org.clockwork.pulse.service.service.impl;

import lombok.SneakyThrows;
import org.clockwork.pulse.service.dao.JobsDaoLayer;
import org.clockwork.pulse.service.dto.request.FetchJobDetailsDto;
import org.clockwork.pulse.service.dto.request.GetCallbackRequestDto;
import org.clockwork.pulse.service.dto.request.PostCallbackRequestDto;
import org.clockwork.pulse.service.dto.response.FetchJobDetailsResponseDto;
import org.clockwork.pulse.service.dto.response.OnboardJobDetailsResponseDto;
import org.clockwork.pulse.service.service.JobsWorkerService;
import org.clockwork.pulse.service.service.util.IdGenerator;
import org.clockwork.pulse.service.utils.ClockworkUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobsWorkerServiceImpl implements JobsWorkerService {

  private static final String JOB_ID_PREFIX = "J";
  private final IdGenerator idGenerator;
  private final JobsDaoLayer jobsDaoLayer;

  @Autowired
  public JobsWorkerServiceImpl(IdGenerator idGenerator, JobsDaoLayer jobsDaoLayer) {
    this.idGenerator = idGenerator;
    this.jobsDaoLayer = jobsDaoLayer;
  }

  @Override
  @SneakyThrows
  public OnboardJobDetailsResponseDto onboardJob(
      PostCallbackRequestDto requestDto) {

    // validate request

    // create entity
    var generatedJobId = getGeneratedId();
    var jobEntity = ClockworkUtility.getJobEntityFromPostDto(requestDto, generatedJobId);
    var jobId = jobsDaoLayer.saveEntity(jobEntity);

    return OnboardJobDetailsResponseDto.builder()
        .jobId(jobId)
        .build();
  }

  @Override
  @SneakyThrows
  public OnboardJobDetailsResponseDto onboardJob(GetCallbackRequestDto requestDto) {

    // validate request

    // create entity
    var generatedJobId = getGeneratedId();
    var jobEntity = ClockworkUtility.getJobEntityFromGetDto(requestDto, generatedJobId);
    var jobId = jobsDaoLayer.saveEntity(jobEntity);

    return OnboardJobDetailsResponseDto.builder()
        .jobId(jobId)
        .build();
  }

  public FetchJobDetailsResponseDto fetchJobDetails(FetchJobDetailsDto requestDto) {

    // validate request

    // fetch entity from db
    var jobEntity = jobsDaoLayer.getJobEntity(requestDto.getJobId());

    return ClockworkUtility.getFetchJobDetailsResponseDto(jobEntity);

  }

  private String getGeneratedId() {
    return idGenerator.generateId(JOB_ID_PREFIX);
  }

}
