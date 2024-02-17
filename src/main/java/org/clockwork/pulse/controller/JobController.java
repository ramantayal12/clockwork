package org.clockwork.pulse.controller;

import static org.clockwork.pulse.utils.ClockworkUtility.getJobEntityFromDto;

import org.clockwork.pulse.dao.JobsDaoLayer;
import org.clockwork.pulse.dto.JobDto;
import org.clockwork.pulse.entity.JobEntity;
import org.clockwork.pulse.utils.service.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/jobs")
public class JobController {

  private static final String JOB_ID_PREFIX = "J";
  private final IdGenerator idGenerator;
  private final JobsDaoLayer jobsDaoLayer;

  @Autowired
  public JobController(IdGenerator idGenerator, JobsDaoLayer jobsDaoLayer) {
    this.idGenerator = idGenerator;
    this.jobsDaoLayer = jobsDaoLayer;
  }

  @PostMapping(path = "/save")
  public ResponseEntity<String> saveJobRequest(@RequestBody JobDto jobDto) {

    var generatedJobId = getGeneratedId();
    var jobEntity = getJobEntityFromDto(jobDto, generatedJobId);

    var resultingId = jobsDaoLayer.saveEntity(jobEntity);

    return ResponseEntity.ok(resultingId);

  }

  @GetMapping(path = "/get/{jobId}")
  public ResponseEntity<JobEntity> getJobDetails(@PathVariable String jobId) {

    var resultingId = jobsDaoLayer.getJobEntity(jobId);
    return ResponseEntity.ok(resultingId);

  }

  private String getGeneratedId() {
    return idGenerator.generateId(JOB_ID_PREFIX);
  }
}
