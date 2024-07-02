package org.clockwork.pulse.service.service;

import org.clockwork.pulse.service.dto.request.FetchJobDetailsDto;
import org.clockwork.pulse.service.dto.request.GetCallbackRequestDto;
import org.clockwork.pulse.service.dto.request.PostCallbackRequestDto;
import org.clockwork.pulse.service.dto.response.FetchJobDetailsResponseDto;
import org.clockwork.pulse.service.dto.response.OnboardJobDetailsResponseDto;
import org.clockwork.pulse.service.exception.BaseClockWorkException;

public interface JobsWorkerService {

  OnboardJobDetailsResponseDto onboardJob(PostCallbackRequestDto requestDto)
      throws BaseClockWorkException;

  OnboardJobDetailsResponseDto onboardJob(GetCallbackRequestDto requestDto)
      throws BaseClockWorkException;

  FetchJobDetailsResponseDto fetchJobDetails(FetchJobDetailsDto jobDetailsDto) throws BaseClockWorkException;

}
