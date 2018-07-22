package com.example.n26.n26transactions.core.statistics;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Calculates sum of data series.
 */
@Service
public class SumCalculator implements StatisticCalculator<Double, Double> {
  @Override
  public Double calculate(List<Double> data) {
    if (data == null || data.isEmpty()){
      return 0.0;
    }
    return data.stream().mapToDouble(Double::doubleValue).sum();
  }
}
