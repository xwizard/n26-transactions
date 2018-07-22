package com.example.n26.n26transactions.core.statistics;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class StatisticsCollectorImplTest {
  private StatisticsCollectorImpl collector;

  @Before
  public void before() {
    collector = new StatisticsCollectorImpl(mockCalculator(10L), mockCalculator(1.0), mockCalculator(2.0), mockCalculator(3.0), mockCalculator(4.0));
  }

  @Test
  public void collectorShouldCollectAllStatistics() {
    StatisticsSnapshot actual = collector.collect(Arrays.asList(1.0, 2.0, 3.0));
    assertEquals(10, actual.getCount());
    assertEquals(1.0, actual.getMax(), 0.1);
    assertEquals(2.0, actual.getAverage(), 0.1);
    assertEquals(3.0, actual.getMin(), 0.1);
    assertEquals(4.0, actual.getSum(), 0.1);
  }

  private <T> StatisticCalculator<Double, T> mockCalculator(T mockData) {
    return data -> mockData;
  }
}