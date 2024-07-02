package org.clockwork.pulse.service.exception.impl;

import org.clockwork.pulse.service.exception.BaseClockWorkException;
import org.clockwork.pulse.service.exception.ErrorDescriptions;
import org.springframework.http.HttpStatus;

public class RequestNotValidException extends BaseClockWorkException {

  public RequestNotValidException() {
    super(ErrorDescriptions.CW0001.getErrorMessage());
  }

  public RequestNotValidException(String message) {
    super(message);
  }

  @Override
  public String getErrorCode() {
    return ErrorDescriptions.CW0001.getErrorCode();
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.BAD_REQUEST;
  }

}
