package org.clockwork.pulse.service.impl;

import static org.clockwork.pulse.utils.ClockworkUtility.getJobEntityFromDto;

import org.clockwork.pulse.dao.JobsDaoLayer;
import org.clockwork.pulse.dto.request.SaveJobRequestDto;
import org.clockwork.pulse.dto.response.FetchJobDetailsResponseDto;
import org.clockwork.pulse.dto.response.SaveJobDetailsResponseDto;
import org.clockwork.pulse.entity.JobEntity;
import org.clockwork.pulse.service.Worker;
import org.clockwork.pulse.service.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerImpl implements Worker {

  private static final String JOB_ID_PREFIX = "J";
  private final IdGenerator idGenerator;
  private final JobsDaoLayer jobsDaoLayer;

  @Autowired
  public WorkerImpl(IdGenerator idGenerator, JobsDaoLayer jobsDaoLayer) {
    this.idGenerator = idGenerator;
    this.jobsDaoLayer = jobsDaoLayer;
  }

  public SaveJobDetailsResponseDto onboardJob(SaveJobRequestDto saveJobRequestDto){

    var generatedJobId = getGeneratedId();
    var jobEntity = getJobEntityFromDto(saveJobRequestDto, generatedJobId);

    var jobId = jobsDaoLayer.saveEntity(jobEntity);

    SaveJobDetailsResponseDto responseDto = SaveJobDetailsResponseDto.builder()
        .jobId(jobId)
        .build();

    return responseDto;
  }

  public FetchJobDetailsResponseDto fetchJobDetails(String jobId){

    JobEntity jobEntity = jobsDaoLayer.getJobEntity(jobId);
    FetchJobDetailsResponseDto responseDto = FetchJobDetailsResponseDto.builder()
        .url(jobEntity.getUrl())
        .requestType(jobEntity.getRequestType())
        .data(jobEntity.getData())
        .callBackTimeAfterMinutes(jobEntity.getExecutionTime())
        .build();

    return responseDto;

  }

  private String getGeneratedId() {
    return idGenerator.generateId(JOB_ID_PREFIX);
  }

}
