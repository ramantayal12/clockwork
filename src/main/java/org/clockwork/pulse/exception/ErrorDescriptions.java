package org.clockwork.pulse.exception;

import lombok.Getter;

@Getter
public enum ErrorDescriptions {

  CW0001("CW0001", "Request Not Valid");

  private final String errorCode;
  private final String errorMessage;
  ErrorDescriptions(String errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }
}
