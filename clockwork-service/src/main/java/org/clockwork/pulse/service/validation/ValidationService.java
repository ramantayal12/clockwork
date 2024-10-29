package org.clockwork.pulse.service.validation;

public interface ValidationService<T> {

  void validate(T request);

}
