package org.clockwork.pulse.service.exception.impl;

import org.clockwork.pulse.service.exception.BaseClockWorkException;
import org.clockwork.pulse.service.exception.ErrorDescriptions;
import org.springframework.http.HttpStatus;

public class NoSuchEntity extends BaseClockWorkException {

  public NoSuchEntity() {
    super(ErrorDescriptions.CW0002.getErrorMessage());
  }

  @Override
  public String getErrorCode() {
    return ErrorDescriptions.CW0002.getErrorCode();
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.NOT_FOUND;
  }
}
