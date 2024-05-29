package org.clockwork.pulse.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EntityScan(basePackages = {
    "org.clockwork.pulse.service"
})
@EnableJpaRepositories(basePackages = {
    "org.clockwork.pulse.service"
})
@ComponentScan(basePackages = {
    "org.clockwork.pulse.service"
})
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {
    "org.clockwork.pulse.service"
})
public class ClockWorkApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClockWorkApplication.class, args);
  }
}
