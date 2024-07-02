package org.clockwork.pulse.service.exception;

import lombok.Getter;

@Getter
public enum ErrorDescriptions {

  CW0001("CW0001", "Request Not Valid"),
  CW0002("CW0002", "Entity Doesn't Exists"),
  CW0003("CW0003", "JobId Not Valid");

  private final String errorCode;
  private final String errorMessage;
  ErrorDescriptions(String errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }
}
