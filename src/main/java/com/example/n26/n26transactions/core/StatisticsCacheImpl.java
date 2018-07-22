package com.example.n26.n26transactions.core;

import com.example.n26.n26transactions.core.statistics.StatisticsSnapshot;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Simple statistic cache implementation relying on {@link AtomicReference}.
 */
@Service
public class StatisticsCacheImpl implements StatisticsCache {

  private final AtomicReference<StatisticsSnapshot> cachedSnapshot = new AtomicReference<>(StatisticsSnapshot.ZERO);

  @Override
  public void set(StatisticsSnapshot snapshot) {
    if (snapshot == null) {
      cachedSnapshot.set(StatisticsSnapshot.ZERO);
    } else {
      cachedSnapshot.set(snapshot);
    }
  }

  @Override
  public StatisticsSnapshot get() {
    return cachedSnapshot.get();
  }
}
