package org.clockwork.pulse.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
  private final ObjectMapper objectMapper;

  @Value(value = "${clockwork.repetition-time-unit}")
  private Long repetitionTime;

  @Value(value = "${spring.kafka.producer.topic}")
  private String KAFKA_PRODUCER_TOPIC;

  @Autowired
  public BatchFetcherImpl(JobsDaoLayer jobsDaoLayer, KafkaJobsProducerService producerService,
      ObjectMapper objectMapper) {
    this.jobsDaoLayer = jobsDaoLayer;
    this.producerService = producerService;
    this.objectMapper = objectMapper;
  }

  /**
   * Note that scheduled tasks don’t run in parallel by default. So even if we used fixedRate, the
   * next task won’t be invoked until the previous one is done.
   */
  @Override
  @Scheduled(fixedRate = 2, timeUnit = TimeUnit.MINUTES)
  public List<String> publishNextBatchOfJobs() {
    List<JobEntity> jobEntities = jobsDaoLayer.getBatchOfJobsBetweenTimestamps(
        System.currentTimeMillis(),
        System.currentTimeMillis() + repetitionTime);

    List<String> responses = new ArrayList<>();
    for (JobEntity job : jobEntities) {

      producerService.sendMessage(KAFKA_PRODUCER_TOPIC, job.getId());
      responses.add(job.getId());

    }

    return responses;
  }
}
