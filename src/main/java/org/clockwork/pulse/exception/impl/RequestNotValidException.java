package org.clockwork.pulse.exception.impl;

import org.clockwork.pulse.exception.BaseClockWorkException;
import org.springframework.http.HttpStatus;

public class RequestNotValidException extends BaseClockWorkException {

  private static final String ERROR_MESSAGE = "Request Not Valid";

  private static final String ERROR_CODE = "CW0001";

  public RequestNotValidException() {
    super(ERROR_MESSAGE);
  }

  @Override
  public String getErrorCode() {
    return ERROR_CODE;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.BAD_REQUEST;
  }

}
