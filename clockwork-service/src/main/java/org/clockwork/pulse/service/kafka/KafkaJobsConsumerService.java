package org.clockwork.pulse.service.kafka;

import org.clockwork.pulse.service.service.JobsExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

/**
 * Run your Spring Boot application and use a tool like Postman or curl to send a POST request to
 * http://localhost:8080/send?message=Hello_Kafka. The message will be sent to the Kafka topic, and
 * the consumer will receive and print the message to the console.
 */
@Service
public class KafkaJobsConsumerService {

  private final String KAFKA_CONSUMER_TOPIC = "jobs-topic-id-1";
  private final String KAFKA_CONSUMER_GROUP_ID = "jobs-consumer-group-1";

  private final JobsExecutor jobsExecutor;

  @Autowired
  public KafkaJobsConsumerService(JobsExecutor jobsExecutor) {
    this.jobsExecutor = jobsExecutor;
  }

  @KafkaListener(topics = KAFKA_CONSUMER_TOPIC, groupId = KAFKA_CONSUMER_GROUP_ID)
  public void listenJob(String jobId, Acknowledgment acknowledgment) {

    jobsExecutor.executeJob(jobId);
    acknowledgment.acknowledge();

  }
}
