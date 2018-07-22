package com.example.n26.n26transactions.core.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsCollectorImpl implements StatisticsCollector {

  private final CountCalculator countCalculator;
  private final MaxCalculator maxCalculator;
  private final MeanCalculator meanCalculator;
  private final MinCalculator minCalculator;
  private final SumCalculator sumCalculator;

  @Autowired
  public StatisticsCollectorImpl(CountCalculator countCalculator, MaxCalculator maxCalculator, MeanCalculator meanCalculator, MinCalculator minCalculator, SumCalculator sumCalculator) {
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
