package com.example.n26.n26transactions.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ClockConfiguration {
  @Bean
  Clock clock() {
    return Clock.systemUTC();
  }
}
