package com.example.n26.n26transactions.core.statistics;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Snapshot of statistics.
 */
public class StatisticsSnapshot {

  public static final StatisticsSnapshot ZERO = new StatisticsSnapshot();

  @JsonIgnore
  private long timestamp = 0 ;
  private double sum = 0;
  private double average = 0;
  private double max = 0;
  private double min = 0;
  private long count = 0;

  private StatisticsSnapshot() {}

  public StatisticsSnapshot(long timestamp, double sum, double average, double max, double min, long count) {
    this.timestamp = timestamp;
    this.sum = sum;
    this.average = average;
    this.max = max;
    this.min = min;
    this.count = count;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public double getSum() {
    return sum;
  }

  public double getAverage() {
    return average;
  }

  public double getMax() {
    return max;
  }

  public double getMin() {
    return min;
  }

  public long getCount() {
    return count;
  }
}
