package com.ss10.controller;


import com.ss10.model.entity.Transaction;
import com.ss10.service.NotificationService;
import com.ss10.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
        Transaction created = transactionService.create(transaction);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}


