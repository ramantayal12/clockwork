package org.clockwork.pulse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EntityScan(basePackages = {"org.clockwork.pulse"})
@EnableJpaRepositories(basePackages = {"org.clockwork.pulse"})
@ComponentScan(basePackages = {"org.clockwork.pulse"})
@EnableJpaAuditing
@SpringBootApplication
public class ClockWorkApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClockWorkApplication.class, args);
  }
}
