package org.clockwork.pulse.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.clockwork.pulse.dao.JobsDaoLayer;
import org.clockwork.pulse.entity.JobEntity;
import org.clockwork.pulse.kafka.KafkaJobsProducerService;
import org.clockwork.pulse.models.RequestType;
import org.clockwork.pulse.service.EventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

class BatchFetcherImplTest {

  private final static int REPETITION_TIME_IN_MINUTES = 2;
  private BatchFetcherImpl batchFetcher;
  private JobsDaoLayer jobsDaoLayer;

  @BeforeEach
  void setUp(){
    jobsDaoLayer = Mockito.mock(JobsDaoLayer.class);
    KafkaJobsProducerService producerService = Mockito.mock(KafkaJobsProducerService.class);
    EventService eventService = Mockito.mock(EventService.class);
    batchFetcher = new BatchFetcherImpl(eventService, jobsDaoLayer, producerService);
  }

  @Test
  void testPublishNextBatchOfJobs_NonNullDataBaseStream(){

    LocalDateTime startOfWindow = LocalDateTime.now();
    LocalDateTime endOfWindow = LocalDateTime.now().plusMinutes(REPETITION_TIME_IN_MINUTES);

    Stream<JobEntity> stream = Stream.<JobEntity>builder()
        .add(getJobEntity_object1())
        .build();
    Mockito.when(jobsDaoLayer.streamBatchOfJobsBetweenTimestamps(startOfWindow, endOfWindow)).thenReturn(stream);
    Assertions.assertDoesNotThrow(
        () -> batchFetcher.extractAndPublishJobsUsingStream(startOfWindow, endOfWindow));
  }

  @Test
  void testPublishNextBatchOfJobs_NullDataBaseStream(){

    LocalDateTime startOfWindow = LocalDateTime.now();
    LocalDateTime endOfWindow = LocalDateTime.now().plusMinutes(REPETITION_TIME_IN_MINUTES);

    Mockito.when(jobsDaoLayer.streamBatchOfJobsBetweenTimestamps(startOfWindow, endOfWindow)).thenReturn(null);
    Assertions.assertDoesNotThrow(
        () -> batchFetcher.extractAndPublishJobsUsingStream(startOfWindow, endOfWindow));
  }

  @Test
  void testPublishNextBatchOfJobsUsingPage_NonNullDataBasePage(){

    LocalDateTime startOfWindow = LocalDateTime.now();
    LocalDateTime endOfWindow = LocalDateTime.now().plusMinutes(REPETITION_TIME_IN_MINUTES);

    List<JobEntity> entities = new ArrayList<>();
    entities.add(getJobEntity_object1());

    Pageable pageable = Mockito.mock(Pageable.class);

    Page<JobEntity> pageObject = new PageImpl<>(entities, pageable, entities.size());
    Mockito.when(
            jobsDaoLayer.pageBatchOfJobsBetweenTimestamps(Mockito.any(), Mockito.any(), Mockito.any()))
        .thenReturn(pageObject);
    Assertions.assertDoesNotThrow(
        () -> batchFetcher.extractAndPublishJobsUsingPage(startOfWindow, endOfWindow));
  }


  @Test
  void testPublishNextBatchOfJobsUsingPage_NullDataBasePage(){

    LocalDateTime startOfWindow = LocalDateTime.now();
    LocalDateTime endOfWindow = LocalDateTime.now().plusMinutes(REPETITION_TIME_IN_MINUTES);

    Mockito.when(
            jobsDaoLayer.pageBatchOfJobsBetweenTimestamps(Mockito.any(), Mockito.any(), Mockito.any()))
        .thenReturn(null);
    Assertions.assertDoesNotThrow(
        () -> batchFetcher.extractAndPublishJobsUsingPage(startOfWindow, endOfWindow));
  }

  private static JobEntity getJobEntity_object1() {
    return JobEntity.builder()
        .jobId("jobId1")
        .data("data1")
        .executionTime(LocalDateTime.now().plusMinutes(1))
        .requestType(RequestType.POST)
        .url("url1")
        .build();
  }

}