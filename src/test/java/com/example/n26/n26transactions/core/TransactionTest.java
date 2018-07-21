package com.example.n26.n26transactions.core;

import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TransactionTest {
  private Clock clock;

  @Before
  public void before() {
    clock = Clock.fixed(Instant.now(), ZoneId.of("UTC"));
  }

  @Test
  public void latestTransactionShouldNotBeExpired() {
    Transaction transaction = new Transaction(10.5, clock.millis());
    assertFalse(transaction.hasExpired(clock));
  }

  @Test
  public void oldTransactionShouldBeExpired() {
    Transaction transaction = new Transaction(10.5, clock.millis() - 60_000);
    assertTrue(transaction.hasExpired(clock));
  }
}