package com.example.n26.n26transactions.core.statistics;

import java.util.List;

/**
 * Facade interface for collecting all statistics in snapshot.
 */
public interface StatisticsCollector {
  /**
   * Collects all known statistics.
   * @param data data to collect from
   * @return {@link StatisticsSnapshot} instance
   */
  StatisticsSnapshot collect(List<Double> data);
}
