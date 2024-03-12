package org.clockwork.pulse.service.impl;

import java.util.Objects;
import lombok.SneakyThrows;
import org.clockwork.pulse.dto.request.GetCallbackRequestDto;
import org.clockwork.pulse.dto.request.PostCallbackRequestDto;
import org.clockwork.pulse.exception.impl.RequestNotValidException;
import org.clockwork.pulse.service.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {


  @Override
  @SneakyThrows
  public void validateGetCallbackRequest(GetCallbackRequestDto requestDto) {

    if(Objects.isNull(requestDto) ){
      throw new RequestNotValidException();
    }
    validateUrl(requestDto.getUrl());

  }

  @Override
  @SneakyThrows
  public void validatePostCallbackRequest(PostCallbackRequestDto requestDto) {

    if(Objects.isNull(requestDto) ){
      throw new RequestNotValidException();
    }
    validateUrl(requestDto.getUrl());
    validateData(requestDto.getData());

  }

  private static void validateData(String data) throws RequestNotValidException {
    if( Objects.isNull(data)){
      throw new RequestNotValidException();
    }
  }

  private static void validateUrl(String url) throws RequestNotValidException {
    if( Objects.isNull(url) ){
      throw new RequestNotValidException();
    }
  }
}
