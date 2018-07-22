package com.example.n26.n26transactions.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class TransactionQueueImpl implements TransactionQueue {

  private final static Logger LOG = LoggerFactory.getLogger(TransactionQueueImpl.class);

  private final Clock clock;
  private final Queue<Transaction> queue;

  @Autowired
  public TransactionQueueImpl(Clock clock) {
    this.clock = clock;
    this.queue = new ConcurrentLinkedDeque<>();
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
  public void putAllNonExpired(Collection<Transaction> transactions) {
    for (Transaction t : transactions) {
      if (!t.hasExpired(clock)) {
        queue.add(t);
      }
    }
  }

  @Override
  public Iterator<Transaction> iterator() {
    return queue.iterator();
  }

  Queue<Transaction> getQueue() {
    return queue;
  }
}
