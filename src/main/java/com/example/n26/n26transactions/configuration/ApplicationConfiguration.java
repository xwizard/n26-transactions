package com.example.n26.n26transactions.configuration;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.Clock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Application context providing custom beans.
 */
@Configuration
public class ApplicationConfiguration {
  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
  Clock clock() {
    return Clock.systemUTC();
  }

  @Bean(destroyMethod = "shutdownNow")
  @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
  ExecutorService executorService() {
    return new ScheduledThreadPoolExecutor(1000);
  }
}
