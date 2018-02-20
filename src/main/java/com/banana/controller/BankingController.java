package com.banana.controller;

import com.banana.model.DecreaseRequest;
import com.banana.model.IncreaseRequest;
import com.banana.model.TransactionEntity;
import com.banana.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.banana.mapping.BankingUrlMapping.*;

@RestController
public class BankingController {

    private final BankingService bankingService;

    @Autowired
    public BankingController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @GetMapping(BALANCE_USER)
    public Long getBalance(@PathVariable String userId) {
        return bankingService.getBalance(userId);
    }

    @GetMapping(HISTORY_USER)
    public List<TransactionEntity> getTransactionHistory(@PathVariable String userId) {
        return bankingService.getTransactionHistory(userId);
    }

    @PostMapping(BALANCE_USER_INCREASE)
    public void increaseFunds(@RequestBody IncreaseRequest request, @PathVariable String userId) {
        bankingService.increaseFunds(request.getValue(), userId);
    }

    @PostMapping(BALANCE_USER_DECREASE)
    public void decreaseFunds(@RequestBody DecreaseRequest request, @PathVariable String userId) {
        bankingService.decreaseFunds(request, userId);
    }
}
