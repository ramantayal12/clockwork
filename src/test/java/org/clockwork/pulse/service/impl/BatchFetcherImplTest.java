package org.clockwork.pulse.service.impl;

import org.clockwork.pulse.dao.JobsDaoLayer;
import org.clockwork.pulse.kafka.KafkaJobsProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

class BatchFetcherImplTest {

  private JobsDaoLayer mockJobsDaoLayer;

  private KafkaJobsProducerService mockProducerService;

  private BatchFetcherImpl batchFetcherImplUnderTest;

  @BeforeEach
  void setUp() {
    mockJobsDaoLayer = Mockito.mock(JobsDaoLayer.class);
    mockProducerService = Mockito.mock(KafkaJobsProducerService.class);
    batchFetcherImplUnderTest = new BatchFetcherImpl(mockJobsDaoLayer, mockProducerService);
    // TODO: Set the following fields: repetitionTime, KAFKA_PRODUCER_TOPIC.
  }

}
