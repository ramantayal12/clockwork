package org.clockwork.pulse.exception.impl;

import static org.clockwork.pulse.exception.ErrorDescriptions.CW0003;

import org.clockwork.pulse.exception.BaseClockWorkException;
import org.springframework.http.HttpStatus;

public class FetchRequestNotValid extends BaseClockWorkException {

  public FetchRequestNotValid() {
    super(CW0003.getErrorMessage());
  }

  @Override
  public String getErrorCode() {
    return CW0003.getErrorCode();
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.BAD_REQUEST;
  }
}
