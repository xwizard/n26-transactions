package com.example.n26.n26transactions.core;

/**
 * Complex (too complex) class processing incoming transaction queue.
 */
public interface TransactionQueueProcessor {
  /**
   * Processes queue exactly once.
   * Processing involves removing expired transactions, calculating statistics and updating transaction cache.
   * Single responsibility principle level 99: it processes :)
   */
  void process();
}
