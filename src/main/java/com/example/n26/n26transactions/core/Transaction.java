package com.example.n26.n26transactions.core;

import java.time.Clock;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents transaction data.
 */
public class Transaction {

  private final static long ONE_MINUTE = 60_000;

  private double amount;
  private long timestamp;
  private UUID id;

  public Transaction(double amount, long timestamp) {
    this.amount = amount;
    this.timestamp = timestamp;
    this.id = UUID.randomUUID();
  }

  /**
   * Checks if transaction has expired.
   * Currently all transaction older than 60s are considered expired.
   * @param clock clock used to determine current time.
   * @return true if transaction has expired.
   */
  public boolean hasExpired(Clock clock) {
    return clock.millis() - ONE_MINUTE >= timestamp;
  }

  public double getAmount() {
    return amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Transaction that = (Transaction) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Transaction{" +
        "amount=" + amount +
        ", timestamp=" + timestamp +
        ", id=" + id +
        '}';
  }
}
