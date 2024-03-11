package org.clockwork.pulse.service.impl;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.clockwork.pulse.dao.JobsDaoLayer;
import org.clockwork.pulse.entity.JobEntity;
import org.clockwork.pulse.kafka.KafkaJobsProducerService;
import org.clockwork.pulse.service.BatchFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    extractAndPublishJobs(startOfWindow, endOfWindow);

  }

  public void extractAndPublishJobs(LocalDateTime startOfWindow, LocalDateTime endOfWindow) {
    Stream<JobEntity> entityStream = jobsDaoLayer.streamBatchOfJobsBetweenTimestamps(
        startOfWindow,
        endOfWindow);

    if (Objects.nonNull(entityStream)) {

      entityStream
          .forEach(job -> producerService.sendMessage(KAFKA_PRODUCER_TOPIC, job.getJobId()));
    }

  }
}
