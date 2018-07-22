package com.example.n26.n26transactions.core.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsCollectorImpl implements StatisticsCollector {

  private final StatisticCalculator<Double, Long> countCalculator;
  private final StatisticCalculator<Double, Double> maxCalculator;
  private final StatisticCalculator<Double, Double> meanCalculator;
  private final StatisticCalculator<Double, Double> minCalculator;
  private final StatisticCalculator<Double, Double> sumCalculator;

  @Autowired
  public StatisticsCollectorImpl(StatisticCalculator<Double, Long> countCalculator, StatisticCalculator<Double, Double> maxCalculator, StatisticCalculator<Double, Double> meanCalculator, StatisticCalculator<Double, Double> minCalculator, StatisticCalculator<Double, Double> sumCalculator) {
    this.countCalculator = countCalculator;
    this.maxCalculator = maxCalculator;
    this.meanCalculator = meanCalculator;
    this.minCalculator = minCalculator;
    this.sumCalculator = sumCalculator;
  }

  @Override
  public StatisticsSnapshot collect(List<Double> data, long timestamp) {
    long count = countCalculator.calculate(data);
    double max = maxCalculator.calculate(data);
    double mean = meanCalculator.calculate(data);
    double min = minCalculator.calculate(data);
    double sum = sumCalculator.calculate(data);

    return new StatisticsSnapshot(timestamp, sum, mean, max, min, count);
  }
}
