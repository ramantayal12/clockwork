package org.clockwork.pulse.entity;

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
import org.clockwork.pulse.entity.base.BaseEntity;
import org.clockwork.pulse.models.RequestType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Builder
//@Document(collection = "jobs") for mongoDb
@Table
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class JobEntity extends BaseEntity {

  private String jobId;

  private String url;

  private String data;

  @Enumerated(EnumType.STRING)
  private RequestType requestType;

  private LocalDateTime executionTime;

}
