package org.clockwork.pulse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.clockwork.pulse.models.RequestType;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {

  private String url;
  private String data;
  private RequestType requestType;
  private Long callBackTimeAfterMinutes;

}
