package com.example.n26.n26transactions.core.statistics;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class CountCalculatorTest {

  private CountCalculator calculator = new CountCalculator();

  @Test
  public void shouldReturn0ForEmptyList() {
    Long actual = calculator.calculate(Collections.emptyList());
    assertEquals(0.0, actual, 0.1);
  }

  @Test
  public void shouldReturn0ForNull() {
    Long actual = calculator.calculate(null);
    assertEquals(0.0, actual, 0.1);
  }

  @Test
  public void shouldReturn3For1_2_3() {
    Long actual = calculator.calculate(Arrays.asList(1.0, 2.0, 3.0));
    assertEquals(Long.valueOf(3L), actual);
  }
}