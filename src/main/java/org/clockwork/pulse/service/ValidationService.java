package org.clockwork.pulse.service;

import org.clockwork.pulse.dto.request.FetchJobDetailsDto;
import org.clockwork.pulse.dto.request.GetCallbackRequestDto;
import org.clockwork.pulse.dto.request.PostCallbackRequestDto;
import org.clockwork.pulse.exception.BaseClockWorkException;

public interface ValidationService {

  void validateFetchRequest(FetchJobDetailsDto requestDto);
  void validateGetCallbackRequest(GetCallbackRequestDto requestDto) throws BaseClockWorkException;
  void validatePostCallbackRequest(PostCallbackRequestDto requestDto) throws BaseClockWorkException;

}
