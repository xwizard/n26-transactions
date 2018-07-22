package com.example.n26.n26transactions.core.statistics;

import java.util.List;

public class CountCalculator implements StatisticCalculator<Double, Integer> {
  @Override
  public Integer calculate(List<Double> data) {
    if (data == null || data.isEmpty()) {
      return 0;
    }
    return data.size();
  }
}
