package org.mpatapenka.issuetracker.api.config;

import org.mpatapenka.issuetracker.api.service.ClockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Clock;

@EnableTransactionManagement
@Configuration(proxyBeanMethods = false)
public class AppConfig {

  @Bean
  public ClockProvider clockProvider() {
    return Clock::systemDefaultZone;
  }
}