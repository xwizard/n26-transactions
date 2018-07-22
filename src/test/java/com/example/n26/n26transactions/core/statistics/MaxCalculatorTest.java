package com.example.n26.n26transactions.core.statistics;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class MaxCalculatorTest {

  private MaxCalculator calculator = new MaxCalculator();

  @Test
  public void shouldReturn0ForEmptyList() {
    Double actual = calculator.calculate(Collections.emptyList());
    assertEquals(0.0, actual, 0.1);
  }

  @Test
  public void shouldReturn0ForNull() {
    Double actual = calculator.calculate(null);
    assertEquals(0.0, actual, 0.1);
  }

  @Test
  public void shouldReturn3For1_2_3() {
    Double actual = calculator.calculate(Arrays.asList(1.0, 2.0, 3.0));
    assertEquals(3.0, actual, 0.1);
  }
}