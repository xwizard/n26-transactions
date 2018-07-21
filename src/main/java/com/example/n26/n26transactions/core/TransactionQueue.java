package com.example.n26.n26transactions.core;

public interface TransactionQueue {
  boolean put(Transaction transaction);
}
