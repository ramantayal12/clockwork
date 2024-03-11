package org.clockwork.pulse.controller;

import org.clockwork.pulse.dto.request.FetchJobDetailsDto;
import org.clockwork.pulse.dto.request.PostCallbackRequestDto;
import org.clockwork.pulse.exception.BaseClockWorkException;
import org.clockwork.pulse.service.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/callbacks")
public class JobController {

  private final Worker worker;

  @Autowired
  public JobController(Worker worker) {
    this.worker = worker;
  }


  @PostMapping(path = "/request-post-callback")
  public ResponseEntity onboardPostJobRequest(
      @RequestBody PostCallbackRequestDto postCallbackRequestDto) {

    try {
      var response = worker.onboardJob(postCallbackRequestDto);
      return ResponseEntity.ok(response);
    } catch (BaseClockWorkException exception){
      return getThrowable(exception);
    }

  }

  @PostMapping(path = "/request-get-callback")
  public ResponseEntity onboardGetJobRequest(
      @RequestBody PostCallbackRequestDto postCallbackRequestDto) {

    try {
      var response = worker.onboardJob(postCallbackRequestDto);
      return ResponseEntity.ok(response);
    }catch (BaseClockWorkException exception){
      return getThrowable(exception);
    }

  }

  @GetMapping(path = "/get-callback-details")
  public ResponseEntity getJobDetails(
      @RequestBody FetchJobDetailsDto jobDetailsDto) {

    try {
      var response = worker.fetchJobDetails(jobDetailsDto.getJobId());
      return ResponseEntity.ok(response);
    }catch (BaseClockWorkException exception){
      return getThrowable(exception);
    }

  }

  private static ResponseEntity<String> getThrowable(BaseClockWorkException exception) {
    return ResponseEntity
        .status(exception.getHttpStatus())
        .body(exception.getMessage());
  }


}
