package com.example.n26.n26transactions.controllers;

import com.example.n26.n26transactions.core.Transaction;
import com.example.n26.n26transactions.core.TransactionQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController {
  private final TransactionQueue transactionQueue;

  @Autowired
  public TransactionController(TransactionQueue transactionQueue) {
    this.transactionQueue = transactionQueue;
  }

  @PostMapping
  public ResponseEntity<String> postTransaction(@RequestBody Transaction transaction) {
    return transactionQueue.put(transaction) ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.noContent().build();
  }
}
