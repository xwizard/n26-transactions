package com.example.n26.n26transactions.core;

import com.example.n26.n26transactions.core.statistics.StatisticsSnapshot;

/**
 * Thread safe statistics cache providing latest and fresh {@link StatisticsSnapshot}.
 */
public interface StatisticsCache {
  /**
   * Sets latest statistics snapshot.
   * @param snapshot {@link StatisticsSnapshot}
   */
  void set(StatisticsSnapshot snapshot);

  /**
   * Returns latest statistics snapshot.
   * @return latest {@link StatisticsSnapshot}
   */
  StatisticsSnapshot get();
}
