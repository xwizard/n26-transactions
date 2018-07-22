package com.example.n26.n26transactions.core;

import java.util.Iterator;

/**
 * Queues incoming transactions for processing.
 */
public interface TransactionQueue {
  /**
   * Puts transaction for further processing if it didn't expire.
   * @param transaction transaction to put
   * @return true if transaction has been added (didn't expire)
   */
  boolean put(Transaction transaction);

  /**
   * Returns queue iterator.
   * @return iterator
   */
  Iterator<Transaction> iterator();
}
