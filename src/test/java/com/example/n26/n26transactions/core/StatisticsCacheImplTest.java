package com.example.n26.n26transactions.core;

import com.example.n26.n26transactions.core.statistics.StatisticsSnapshot;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatisticsCacheImplTest {

  private StatisticsCache cache = new StatisticsCacheImpl();

  @Test
  public void setShouldSet0OnNull() {
    cache.set(null);
    StatisticsSnapshot actual = cache.get();

    assertEquals(StatisticsSnapshot.ZERO, actual);
  }

  @Test
  public void setShouldSetStatistic() {
    StatisticsSnapshot statisticsSnapshot = new StatisticsSnapshot(0,0,0,0,0);
    cache.set(statisticsSnapshot);
    StatisticsSnapshot actual = cache.get();

    assertEquals(statisticsSnapshot, actual);
  }
}