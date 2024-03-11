package org.clockwork.pulse.exception;

import org.clockwork.pulse.models.ErrorDomain;
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
