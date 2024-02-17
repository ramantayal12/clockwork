package org.clockwork.pulse.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.clockwork.pulse.models.RequestType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "jobs")
public class JobEntity {

  @Id
  private String id;
  private String url;
  private String data;
  private RequestType requestType;
  private Long executionTime;

}
