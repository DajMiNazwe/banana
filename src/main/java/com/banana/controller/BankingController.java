package com.banana.controller;

import com.banana.model.DecreaseRequest;
import com.banana.model.IncreaseRequest;
import com.banana.model.TransactionHistory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankingController {

    @GetMapping
    public String getBalance() {
        return " ";
    }

    @GetMapping
    public TransactionHistory getTransactionHistory() {
        return new TransactionHistory();
    }

    @PostMapping
    public void increaseFunds(IncreaseRequest request) {

    }

    @PostMapping
    public void decreaseFunds(DecreaseRequest request) {

    }
}
