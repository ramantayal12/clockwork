package org.clockwork.pulse.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.clockwork.pulse.models.RequestType;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveJobRequestDto {

  private String url;
  private String data;
  private RequestType requestType;
  private Long callBackTimeAfterMinutes;

}
