package com.example.n26.n26transactions.core.statistics;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Calculates data count.
 */
@Service
public class CountCalculator implements StatisticCalculator<Double, Long> {
  @Override
  public Long calculate(List<Double> data) {
    if (data == null || data.isEmpty()) {
      return 0L;
    }
    return (long) data.size();
  }
}
