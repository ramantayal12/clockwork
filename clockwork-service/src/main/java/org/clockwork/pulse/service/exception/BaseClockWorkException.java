package org.clockwork.pulse.service.exception;

import org.clockwork.pulse.service.models.ErrorDomain;
import org.springframework.http.HttpStatus;

public abstract class BaseClockWorkException extends Exception{

  public BaseClockWorkException(String message) {
    super(message);
  }

  public abstract String getErrorCode();

  public abstract HttpStatus getHttpStatus();

  public ErrorDomain getErrorDomain() {
    return ErrorDomain.CLOCKWORK;
  }
}
