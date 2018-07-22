package com.example.n26.n26transactions.controllers;

import com.example.n26.n26transactions.core.Transaction;
import com.example.n26.n26transactions.core.TransactionQueue;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Iterator;

import static org.junit.Assert.*;

public class TransactionControllerTest {
  @Test
  public void postTransactionShouldReturnHttpCreatedForNonExpiredTransaction() {
    TransactionController controller = controller(true);
    ResponseEntity<String> actual = controller.postTransaction(null);

    assertEquals(HttpStatus.CREATED, actual.getStatusCode());
  }

  @Test
  public void postTransactionShouldReturnHttpNoContentForExpiredTransaction() {
    TransactionController controller = controller(false);
    ResponseEntity<String> actual = controller.postTransaction(null);

    assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
  }

  private TransactionController controller(boolean transactionAdded) {
    return new TransactionController(new TransactionQueue() {
      @Override
      public boolean put(Transaction transaction) {
        return transactionAdded;
      }

      @Override
      public Iterator<Transaction> iterator() {
        return null;
      }
    });
  }
}