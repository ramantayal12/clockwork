package org.clockwork.pulse.controller;

import org.clockwork.pulse.dto.request.FetchJobDetailsDto;
import org.clockwork.pulse.dto.request.SaveJobRequestDto;
import org.clockwork.pulse.dto.response.FetchJobDetailsResponseDto;
import org.clockwork.pulse.dto.response.SaveJobDetailsResponseDto;
import org.clockwork.pulse.service.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/jobs")
public class JobController {

  private final Worker worker;

  @Autowired
  public JobController(Worker worker) {
    this.worker = worker;
  }


  @PostMapping(path = "/save")
  public ResponseEntity<SaveJobDetailsResponseDto> saveJobRequest(@RequestBody SaveJobRequestDto saveJobRequestDto) {

    var response = worker.onboardJob(saveJobRequestDto);
    return ResponseEntity.ok(response);

  }

  @GetMapping(path = "/get")
  public ResponseEntity<FetchJobDetailsResponseDto> getJobDetails(@RequestBody FetchJobDetailsDto jobDetailsDto) {

    var response = worker.fetchJobDetails(jobDetailsDto.getJobId());
    return ResponseEntity.ok(response);

  }


}
