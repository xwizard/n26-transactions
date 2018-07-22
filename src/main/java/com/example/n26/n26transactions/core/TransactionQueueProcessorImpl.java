package com.example.n26.n26transactions.core;

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

@Service
public class TransactionQueueProcessorImpl implements Callable<String>, TransactionQueueProcessor {

  private final static Logger LOG = LoggerFactory.getLogger(TransactionQueueProcessorImpl.class);

  private final ExecutorService executorService;
  private final TransactionQueue transactionQueue;
  private final Clock clock;

  @Autowired
  public TransactionQueueProcessorImpl(ExecutorService executorService, TransactionQueue transactionQueue, Clock clock) {
    this.executorService = executorService;
    this.transactionQueue = transactionQueue;
    this.clock = clock;
  }

  @PostConstruct
  public void postConstruct() {
    LOG.debug("TransactionQueueProcessorImpl post construct");

    Callable<String> callable = new TransactionQueueProcessorImpl(executorService, transactionQueue, clock);
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
    }

    long end = clock.millis();

    LOG.debug("Processed {} transactions in {}ms", i, end - start);
  }
}
