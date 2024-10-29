package org.clockwork.pulse.service.validation.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.clockwork.pulse.service.validation.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl<T> implements ValidationService<T> {

  public final static String VALIDATIONS_FAILED_TEXT = "validations failed";
  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();

  @Override
  public void validate(T request) {
    Set<ConstraintViolation<T>> violations = validator.validate(request);

    if( !violations.isEmpty() ) {
      throw new ConstraintViolationException(VALIDATIONS_FAILED_TEXT, violations);
    }
  }
}
