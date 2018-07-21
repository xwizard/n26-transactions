package com.example.n26.n26transactions.core;

import java.util.Objects;
import java.util.UUID;

public class Transaction {
  private double amount;
  private long timestamp;
  private UUID id;

  private Transaction() {}

  public Transaction(double amount, long timestamp) {
    this.amount = amount;
    this.timestamp = timestamp;
    this.id = UUID.randomUUID();
  }

  public double getAmount() {
    return amount;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public UUID getId() {
    return id;
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
}
