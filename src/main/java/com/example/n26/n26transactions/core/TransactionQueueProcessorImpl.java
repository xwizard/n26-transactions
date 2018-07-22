package com.example.n26.n26transactions.core;

import com.example.n26.n26transactions.core.statistics.StatisticCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Clock;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class TransactionQueueProcessorImpl implements Callable<String>, TransactionQueueProcessor {

  private final static Logger LOG = LoggerFactory.getLogger(TransactionQueueProcessorImpl.class);

  private final ExecutorService executorService;
  private final TransactionQueue transactionQueue;
  private final Clock clock;
  private final List<StatisticCalculator> calculators;

  @Autowired
  public TransactionQueueProcessorImpl(ExecutorService executorService, TransactionQueue transactionQueue, Clock clock, List<StatisticCalculator> calculators) {
    this.executorService = executorService;
    this.transactionQueue = transactionQueue;
    this.clock = clock;
    this.calculators = calculators;
  }

  @PostConstruct
  public void postConstruct() {
    LOG.debug("TransactionQueueProcessorImpl post construct");

    Callable<String> callable = new TransactionQueueProcessorImpl(executorService, transactionQueue, clock, calculators);
    executorService.submit(callable);
  }

  @Override
  public String call() throws Exception {
    while (true) {
      process();
      Thread.sleep(100);
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
      }
      i++;
      currentTransactions.add(transaction);
    }

    calculators.forEach(calculator -> {
      calculator.calculate(currentTransactions.stream().map(Transaction::getAmount).collect(Collectors.toList()));
    });

    long end = clock.millis();

    LOG.debug("Processed {} transactions in {}ms", i, end - start);
  }
}
