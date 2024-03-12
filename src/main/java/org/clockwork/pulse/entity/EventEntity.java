package org.clockwork.pulse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.clockwork.pulse.entity.base.BaseEntity;
import org.clockwork.pulse.models.JobStatus;

@Getter
@Setter
@Builder
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity extends BaseEntity {

  private String eventId;
  private String workflowId;
  private String jobId;
  private JobStatus jobStatus;

}
