package com.example.n26.n26transactions.core;

import com.example.n26.n26transactions.core.statistics.StatisticsCollector;
import com.example.n26.n26transactions.core.statistics.StatisticsSnapshot;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Normally I'd use mockito. But I only have two stubs. Plus mockito gives me no advantage with those two.
 */
class StubStatisticsCollector implements StatisticsCollector {

  private List<Double> data = Collections.emptyList();

  @Override
  public StatisticsSnapshot collect(List<Double> data, long timestamp) {
    this.data = data;
    return StatisticsSnapshot.ZERO;
  }

  public void assertDataCount(int count) {
    assertEquals(count, data.size());
  }
}
