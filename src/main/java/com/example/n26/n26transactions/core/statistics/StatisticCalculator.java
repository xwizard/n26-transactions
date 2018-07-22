package com.example.n26.n26transactions.core.statistics;

import java.util.List;

/**
 * Calculates statistic for given data
 * @param <T> type of data
 */
public interface StatisticCalculator<T> {
  T calculate(List<T> data);
}
