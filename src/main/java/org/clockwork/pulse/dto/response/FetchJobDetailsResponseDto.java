package org.clockwork.pulse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.clockwork.pulse.models.RequestType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FetchJobDetailsResponseDto {

  private String url;
  private String data;
  private RequestType requestType;
  private Long callBackTimeAfterMinutes;

}
