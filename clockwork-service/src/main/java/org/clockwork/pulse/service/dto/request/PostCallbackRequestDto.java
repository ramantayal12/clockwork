package org.clockwork.pulse.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCallbackRequestDto {

  private String url;
  private String data;
  private Long callBackTimeAfterMinutes;

}
