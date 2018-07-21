package com.example.n26.n26transactions.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class TransactionQueueImpl implements TransactionQueue {

  private final static long ONE_MINUTE = 60_000;

  private final Clock clock;
  private final Queue<Transaction> queue;

  @Autowired
  public TransactionQueueImpl(Clock clock) {
    this.clock = clock;
    this.queue = new ConcurrentLinkedDeque<>();
  }

  @Override
  public boolean put(Transaction transaction) {
    if (clock.millis() - ONE_MINUTE < transaction.getTimestamp() ) {
      queue.add(transaction);
      return true;
    }

    return false;
  }

  Queue<Transaction> getQueue() {
    return queue;
  }
}
