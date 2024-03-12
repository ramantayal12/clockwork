package org.clockwork.pulse.workflow;

import static org.clockwork.pulse.contansts.Constants.WORKFLOW_ID_PREFIX;

import java.util.Objects;
import lombok.Getter;
import org.clockwork.pulse.service.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkflowManager {
  private final IdGenerator idGenerator;
  private String workflowId;

  @Autowired
  public WorkflowManager(IdGenerator idGenerator) {
    this.idGenerator = idGenerator;
  }

  public String getWorkflowId() {
    if(Objects.isNull(workflowId) ){
      workflowId = idGenerator.generateId(WORKFLOW_ID_PREFIX);
    }
    return workflowId;
  }

}
