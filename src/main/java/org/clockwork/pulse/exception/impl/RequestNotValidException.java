package org.clockwork.pulse.exception.impl;

import static org.clockwork.pulse.exception.ErrorDescriptions.CW0001;

import org.clockwork.pulse.exception.BaseClockWorkException;
import org.springframework.http.HttpStatus;

public class RequestNotValidException extends BaseClockWorkException {

  public RequestNotValidException() {
    super(CW0001.getErrorMessage());
  }

  public RequestNotValidException(String message) {
    super(message);
  }

  @Override
  public String getErrorCode() {
    return CW0001.getErrorCode();
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.BAD_REQUEST;
  }

}
