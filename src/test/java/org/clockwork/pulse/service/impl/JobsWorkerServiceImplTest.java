package org.clockwork.pulse.service.impl;

import org.clockwork.pulse.dao.JobsDaoLayer;
import org.clockwork.pulse.dto.request.FetchJobDetailsDto;
import org.clockwork.pulse.dto.request.GetCallbackRequestDto;
import org.clockwork.pulse.dto.request.PostCallbackRequestDto;
import org.clockwork.pulse.dto.response.OnboardJobDetailsResponseDto;
import org.clockwork.pulse.service.EventService;
import org.clockwork.pulse.service.ValidationService;
import org.clockwork.pulse.service.util.IdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class JobsWorkerServiceImplTest {

  private IdGenerator idGenerator;
  private JobsDaoLayer jobsDaoLayer;
  private ValidationService validationService;
  private EventService eventService;
  private JobsWorkerServiceImpl jobsWorkerService;

  @BeforeEach
  void setUp() {

    idGenerator = Mockito.mock(IdGenerator.class);
    jobsDaoLayer = Mockito.mock(JobsDaoLayer.class);
    validationService = Mockito.mock(ValidationService.class);
    eventService = Mockito.mock(EventService.class);
    jobsWorkerService = new JobsWorkerServiceImpl(idGenerator, jobsDaoLayer, validationService,
        eventService);

  }

  @Test
  void testPostOnboardJob_Success(){

    var jobId = "J12345";
    var data = "data";
    var url = "url";

    var postCallbackRequestDto = PostCallbackRequestDto.builder()
        .url(url)
        .data(data)
        .callBackTimeAfterMinutes(5)
        .build();

    Mockito.when(idGenerator.generateId("J"))
        .thenReturn(jobId);
    Mockito.when(jobsDaoLayer.saveEntity(Mockito.any()))
        .thenReturn(jobId);

    var actualResponse = jobsWorkerService.onboardJob(postCallbackRequestDto);
    var expectedResponse = OnboardJobDetailsResponseDto.builder()
        .jobId(jobId)
        .build();

    // Expected -> Actual
    Assertions.assertEquals(expectedResponse.getJobId(), actualResponse.getJobId());

  }

  @Test
  void testGetOnboardJob_Success(){

    var jobId = "J12345";
    var url = "url";

    var requestDto = GetCallbackRequestDto.builder()
        .url(url)
        .callBackTimeAfterMinutes(5)
        .build();

    Mockito.when(idGenerator.generateId("J"))
        .thenReturn(jobId);
    Mockito.when(jobsDaoLayer.saveEntity(Mockito.any()))
        .thenReturn(jobId);

    var actualResponse = jobsWorkerService.onboardJob(requestDto);
    var expectedResponse = OnboardJobDetailsResponseDto.builder()
        .jobId(jobId)
        .build();

    // Expected -> Actual
    Assertions.assertEquals(expectedResponse.getJobId(), actualResponse.getJobId());

  }

  @Test
  void testFetchJobDetails_Success(){

    FetchJobDetailsDto fetchJobDetailsDto = FetchJobDetailsDto.builder()

        .build();
  }



}