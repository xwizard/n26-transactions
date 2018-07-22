package com.example.n26.n26transactions.core;

import com.example.n26.n26transactions.core.statistics.StatisticsSnapshot;

/**
 * Thread safe statistics cache providing latest and fresh {@link StatisticsSnapshot}.
 */
public interface StatisticsCache {
  void set(StatisticsSnapshot snapshot);
  StatisticsSnapshot get();
}
