package com.example.n26.n26transactions.core.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Snapshot of statistics.
 */
public class StatisticsSnapshot {

  public static final StatisticsSnapshot ZERO = new StatisticsSnapshot();

  private double sum = 0;
  private double mean = 0;
  private double max = 0;
  private double min = 0;
  private long count = 0;

  private StatisticsSnapshot() {}

  public StatisticsSnapshot(double sum, double mean, double max, double min, long count) {
    this.sum = sum;
    this.mean = mean;
    this.max = max;
    this.min = min;
    this.count = count;
  }

  public double getSum() {
    return sum;
  }

  @JsonProperty("avg")
  public double getMean() {
    return mean;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StatisticsSnapshot snapshot = (StatisticsSnapshot) o;
    return Double.compare(snapshot.sum, sum) == 0 &&
        Double.compare(snapshot.mean, mean) == 0 &&
        Double.compare(snapshot.max, max) == 0 &&
        Double.compare(snapshot.min, min) == 0 &&
        count == snapshot.count;
  }

  @Override
  public int hashCode() {

    return Objects.hash(sum, mean, max, min, count);
  }

  @Override
  public String toString() {
    return "StatisticsSnapshot{" +
        "sum=" + sum +
        ", mean=" + mean +
        ", max=" + max +
        ", min=" + min +
        ", count=" + count +
        '}';
  }
}
