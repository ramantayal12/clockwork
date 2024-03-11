package org.clockwork.pulse.service;

import org.clockwork.pulse.dto.request.SaveJobRequestDto;
import org.clockwork.pulse.dto.response.FetchJobDetailsResponseDto;
import org.clockwork.pulse.dto.response.SaveJobDetailsResponseDto;
import org.clockwork.pulse.entity.JobEntity;

public interface Worker {

  SaveJobDetailsResponseDto onboardJob(SaveJobRequestDto saveJobRequestDto);

  FetchJobDetailsResponseDto fetchJobDetails(String jobId);

}
