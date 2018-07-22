package com.example.n26.n26transactions.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Simple implementation of {@link TransactionQueue} using {@link ConcurrentLinkedDeque}.
 */
@Service
public class TransactionQueueImpl implements TransactionQueue {

  private final static Logger LOG = LoggerFactory.getLogger(TransactionQueueImpl.class);

  private final Clock clock;
  private final Queue<Transaction> queue;

  @Autowired
  public TransactionQueueImpl(Clock clock) {
    this.clock = clock;
    this.queue = new ConcurrentLinkedQueue<>();
  }

  @Override
  public boolean put(Transaction transaction) {
    if (!transaction.hasExpired(clock)) {
      queue.add(transaction);
      return true;
    }

    LOG.debug("Transaction expired {}", transaction.toString());
    return false;
  }

  @Override
  public Iterator<Transaction> iterator() {
    return queue.iterator();
  }

  Queue<Transaction> getQueue() {
    return queue;
  }
}
