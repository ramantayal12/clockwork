package org.clockwork.pulse.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.clockwork.pulse.models.JobStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

  private String jobId;
  private JobStatus jobStatus;

}
