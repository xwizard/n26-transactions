package com.example.n26.n26transactions.core;

import com.example.n26.n26transactions.core.statistics.StatisticsSnapshot;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class TransactionQueueProcessorImplTest {
  private TransactionQueueProcessorImpl processor;
  private StubTransactionQueue queue;
  private StubStatisticsCollector collector;
  private Clock clock;

  @Before
  public void before() {
    clock = Clock.fixed(Instant.now(), ZoneId.of("UTC"));
    queue = new StubTransactionQueue();
    collector = new StubStatisticsCollector();
    processor = new TransactionQueueProcessorImpl(null, queue, clock, collector, mockCache());
  }

  @Test
  public void processShouldCleanExpiredTransactions() {
    queue.put(transaction(0));
    queue.put(transaction(0));

    processor.process();

    queue.assertTransactionCount(0);
    collector.assertDataCount(0);
  }

  @Test
  public void processShouldProcessNonExpiredTransactions() {
    queue.put(transaction(clock.millis()));
    queue.put(transaction(clock.millis()));

    processor.process();

    queue.assertTransactionCount(2);
    collector.assertDataCount(2);
  }

  @Test
  public void processShouldProcessNonExpiredTransactionsAndCleanExpiredTransactions() {
    queue.put(transaction(clock.millis()));
    queue.put(transaction(0));

    processor.process();

    queue.assertTransactionCount(1);
    collector.assertDataCount(1);
  }

  private StatisticsCache mockCache() {
    return new StatisticsCache() {
      @Override
      public void set(StatisticsSnapshot snapshot) {
      }

      @Override
      public StatisticsSnapshot get() {
        return null;
      }
    };
  }

  private Transaction transaction(long timestamp) {
    return new Transaction(10.0, timestamp);
  }
}