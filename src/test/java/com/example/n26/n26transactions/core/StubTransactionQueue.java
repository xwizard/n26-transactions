package com.example.n26.n26transactions.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

/**
 * Normally I'd use Mockito, but for two stubs… why bother?
 */
class StubTransactionQueue implements TransactionQueue {

  private final Queue<Transaction> queue = new LinkedList<>();

  @Override
  public boolean put(Transaction transaction) {
    return queue.add(transaction);
  }

  @Override
  public Iterator<Transaction> iterator() {
    return queue.iterator();
  }

  public void assertTransactionCount(int count) {
    assertEquals(count, queue.size());
  }
}
