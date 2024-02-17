package org.clockwork.pulse.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaJobsProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  public KafkaJobsProducerService(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String topic, String jobEntity) {
    kafkaTemplate.send(topic, jobEntity);
  }

}
