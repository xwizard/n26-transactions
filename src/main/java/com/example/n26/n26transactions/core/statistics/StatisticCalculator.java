package com.example.n26.n26transactions.core.statistics;

import java.util.List;

/**
 * Calculates statistic for given data.
 * @param <S> data series type
 * @param <R> return type
 */
public interface StatisticCalculator<S, R> {
  /**
   * Calculates statistic for given data.
   * @param data data series
   * @return statistic value
   */
  R calculate(List<S> data);
}
