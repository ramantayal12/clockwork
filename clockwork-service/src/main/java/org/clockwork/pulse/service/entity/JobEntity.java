package org.clockwork.pulse.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.clockwork.pulse.service.entity.base.BaseEntity;
import org.clockwork.pulse.service.models.RequestType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class JobEntity extends BaseEntity {

  @Column(name = "job_id")
  private String jobId;

  @Column(name = "url")
  private String url;

  @Column(name = "data")
  private String data;

  @Enumerated(EnumType.STRING)
  @Column(name = "request_type")
  private RequestType requestType;

  @Column(name = "execution_time")
  private LocalDateTime executionTime;

}
