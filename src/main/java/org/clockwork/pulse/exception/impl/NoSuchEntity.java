package org.clockwork.pulse.exception.impl;

import static org.clockwork.pulse.exception.ErrorDescriptions.CW0001;
import static org.clockwork.pulse.exception.ErrorDescriptions.CW0002;

import org.clockwork.pulse.exception.BaseClockWorkException;
import org.springframework.http.HttpStatus;

public class NoSuchEntity extends BaseClockWorkException {

  public NoSuchEntity() {
    super(CW0002.getErrorMessage());
  }

  @Override
  public String getErrorCode() {
    return CW0002.getErrorCode();
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.NOT_FOUND;
  }
}
