package com.example.n26.n26transactions.core;

import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class TransactionQueueImplTest {
  private TransactionQueueImpl queue;
  private Clock clock;

  @Before
  public void before() {
    clock = Clock.fixed(Instant.now(), ZoneId.of("UTC"));
    queue = new TransactionQueueImpl(clock);
  }

  @Test
  public void latestTransactionShouldBeAddedToQueue() {
    Transaction transaction = new Transaction(10.5, clock.millis());
    queue.put(transaction);

    Queue<Transaction> actual = queue.getQueue();
    assertEquals(1, actual.size());
    assertEquals(transaction, actual.poll());
  }

  @Test
  public void oldTransactionShouldNotBeAddedToQueue() {
    Transaction transaction = new Transaction(10.5, clock.millis() - 60_000);
    queue.put(transaction);

    Queue<Transaction> actual = queue.getQueue();
    assertEquals(0, actual.size());
  }
}