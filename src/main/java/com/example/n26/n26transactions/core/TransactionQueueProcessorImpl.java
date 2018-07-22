package com.example.n26.n26transactions.core;

import com.example.n26.n26transactions.core.statistics.StatisticsCollector;
import com.example.n26.n26transactions.core.statistics.StatisticsSnapshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Clock;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * Default and only implementation.
 * Implements {@link Runnable} and submits itself to {@link Executor}.
 * Processes incoming transactions in infinite loop.
 */
@Service
public class TransactionQueueProcessorImpl implements Runnable, TransactionQueueProcessor {

  private final static Logger LOG = LoggerFactory.getLogger(TransactionQueueProcessorImpl.class);

  private final Executor executor;
  private final TransactionQueue transactionQueue;
  private final Clock clock;
  private final StatisticsCollector statisticsCollector;
  private final StatisticsCache statisticsCache;

  @Autowired
  public TransactionQueueProcessorImpl(Executor executor, TransactionQueue transactionQueue, Clock clock, StatisticsCollector statisticsCollector, StatisticsCache statisticsCache) {
    this.executor = executor;
    this.transactionQueue = transactionQueue;
    this.clock = clock;
    this.statisticsCollector = statisticsCollector;
    this.statisticsCache = statisticsCache;
  }

  @PostConstruct
  public void postConstruct() {
    LOG.debug("TransactionQueueProcessorImpl post construct");

    executor.execute(this);
  }

  @Override
  public void run() {
    while (true) {
      process();
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        LOG.error("Error while putting thread to sleep: ", e);
      }
    }
  }

  @Override
  public void process() {
    LOG.debug("TransactionQueueProcessorImpl processes");
    List<Transaction> currentTransactions = new LinkedList<>();
    Iterator<Transaction> iterator = transactionQueue.iterator();

    int i = 0;
    long start = clock.millis();

    while (iterator.hasNext()) {
      Transaction transaction = iterator.next();
      if (transaction.hasExpired(clock)) {
        iterator.remove();
        continue;
      }
      i++;
      currentTransactions.add(transaction);
    }

    StatisticsSnapshot snapshot = statisticsCollector.collect(currentTransactions.stream()
        .map(Transaction::getAmount)
        .collect(Collectors.toList()), clock.millis());

    statisticsCache.set(snapshot);

    long end = clock.millis();

    LOG.debug("Processed {} transactions in {}ms", i, end - start);
  }
}
