package org.clockwork.pulse.service.service.impl;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.clockwork.pulse.service.dao.JobsDaoLayer;
import org.clockwork.pulse.service.entity.JobEntity;
import org.clockwork.pulse.service.kafka.KafkaJobsProducerService;
import org.clockwork.pulse.service.service.BatchFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BatchFetcherImpl implements BatchFetcher {

  private final JobsDaoLayer jobsDaoLayer;
  private final KafkaJobsProducerService producerService;

  @Value(value = "${clockwork.repetition-time-unit-minutes}")
  private Long repetitionTimeInMinutes;

  @Value(value = "${spring.kafka.producer.topic}")
  private String KAFKA_PRODUCER_TOPIC;

  @Autowired
  public BatchFetcherImpl(JobsDaoLayer jobsDaoLayer, KafkaJobsProducerService producerService) {
    this.jobsDaoLayer = jobsDaoLayer;
    this.producerService = producerService;
  }

  /**
   * Note that scheduled tasks don’t run in parallel by default. So even if we used fixedRate, the
   * next task won’t be invoked until the previous one is done.
   */
  @Override
  @Scheduled(fixedRate = 2, timeUnit = TimeUnit.MINUTES)
  public void cronMethod(){
    this.publishNextBatchOfJobs();
  }

  public void publishNextBatchOfJobs() {

    LocalDateTime startOfWindow = LocalDateTime.now();
    LocalDateTime endOfWindow = LocalDateTime.now().plusMinutes(repetitionTimeInMinutes);

    extractAndPublishJobsUsingPage(startOfWindow, endOfWindow);

  }

  public void extractAndPublishJobsUsingStream(LocalDateTime startOfWindow, LocalDateTime endOfWindow) {
    Stream<JobEntity> entityStream = jobsDaoLayer.streamBatchOfJobsBetweenTimestamps(
        startOfWindow,
        endOfWindow);

    if (Objects.nonNull(entityStream)) {

      entityStream
          .forEach(job -> producerService.sendMessage(KAFKA_PRODUCER_TOPIC, job.getJobId()));
    }
  }

  public void extractAndPublishJobsUsingPage(LocalDateTime startOfWindow, LocalDateTime endOfWindow) {

    int pageNumber = 0;
    int pageSize = 100;
    PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
    Page<JobEntity> entities = jobsDaoLayer.pageBatchOfJobsBetweenTimestamps(
        startOfWindow,
        endOfWindow, pageRequest);

    while( entities.hasNext() ){

      for(JobEntity entity : entities) {
        producerService.sendMessage(KAFKA_PRODUCER_TOPIC, entity.getJobId());
      }

      pageNumber += 1;
      pageRequest = PageRequest.of(pageNumber, pageSize);
      entities = jobsDaoLayer.pageBatchOfJobsBetweenTimestamps(
          startOfWindow,
          endOfWindow, pageRequest);

    }
  }
}
