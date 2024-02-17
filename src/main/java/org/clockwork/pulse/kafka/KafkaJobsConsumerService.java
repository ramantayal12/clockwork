package org.clockwork.pulse.kafka;

import org.clockwork.pulse.service.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
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

  private final Executor executor;

  @Autowired
  public KafkaJobsConsumerService(Executor executor) {
    this.executor = executor;
  }

  @KafkaListener(topics = KAFKA_CONSUMER_TOPIC, groupId = KAFKA_CONSUMER_GROUP_ID)
  public void listenJob(String jobId) {

    executor.executeJob(jobId);

  }
}
