package org.clockwork.pulse.service;

import org.clockwork.pulse.dto.request.GetCallbackRequestDto;
import org.clockwork.pulse.dto.request.PostCallbackRequestDto;
import org.clockwork.pulse.dto.response.FetchJobDetailsResponseDto;
import org.clockwork.pulse.dto.response.OnboardJobDetailsResponseDto;
import org.clockwork.pulse.exception.BaseClockWorkException;

public interface Worker {

  OnboardJobDetailsResponseDto onboardJob(PostCallbackRequestDto requestDto) throws BaseClockWorkException;
  OnboardJobDetailsResponseDto onboardJob(GetCallbackRequestDto requestDto) throws BaseClockWorkException;

  FetchJobDetailsResponseDto fetchJobDetails(String jobId) throws BaseClockWorkException;

}
