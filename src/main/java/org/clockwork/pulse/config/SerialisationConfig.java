package org.clockwork.pulse.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerialisationConfig {

  @Bean
  public ObjectMapper init() {

    // Creating and returning a new instance of ObjectMapper by default singleton
    return new ObjectMapper();

  }

}
