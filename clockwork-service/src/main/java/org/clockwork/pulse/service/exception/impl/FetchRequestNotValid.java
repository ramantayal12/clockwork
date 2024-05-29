package org.clockwork.pulse.service.exception.impl;

import org.clockwork.pulse.service.exception.BaseClockWorkException;
import org.clockwork.pulse.service.exception.ErrorDescriptions;
import org.springframework.http.HttpStatus;

public class FetchRequestNotValid extends BaseClockWorkException {

  public FetchRequestNotValid() {
    super(ErrorDescriptions.CW0003.getErrorMessage());
  }

  @Override
  public String getErrorCode() {
    return ErrorDescriptions.CW0003.getErrorCode();
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.BAD_REQUEST;
  }
}
