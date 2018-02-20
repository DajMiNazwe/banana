package com.banana.service;

import com.banana.model.AccountEntity;
import com.banana.model.TransactionEntity;
import com.banana.repository.AccountRepository;
import com.banana.repository.TransactionRepository;
import com.banana.shared.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class BankingService {

    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    @Autowired
    public BankingService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public Long getBalance(String id) {
        Optional<AccountEntity> account = accountRepository.findOneByOwnerId(id);
        if (account.isPresent()) {
            return account.get().getBalance();
        } else {
            throw new AccountNotFoundException(id);
        }
    }

    public List<TransactionEntity> getTransactionHistory(String id) {
        return transactionRepository.findAllByOwnerId(id);
    }

    @Transactional
    public void increaseFunds(Long value, String id) {
        Optional<AccountEntity> optionalAccount = accountRepository.findOneByOwnerId(id);
        AccountEntity account = optionalAccount.orElseGet(() -> createNewAccount(id));

        TransactionEntity transaction = new TransactionEntity();
        transaction.setOwnerId(id);
        transaction.setType(TransactionEntity.TransactionType.INCREASE);
        transaction.setValue(value);
        transaction.setOperationDate(Instant.now());
        account.setBalance(account.getBalance() + value);
        accountRepository.save(account);
    }

    public void decreaseFunds(Long value, String id) {
        Optional<AccountEntity> optionalAccount = accountRepository.findOneByOwnerId(id);
        AccountEntity account = optionalAccount.orElseThrow(() -> new AccountNotFoundException(id));

        TransactionEntity transaction = new TransactionEntity();
        transaction.setOwnerId(id);
        transaction.setType(TransactionEntity.TransactionType.DECREASE);
        transaction.setValue(value);
        transaction.setOperationDate(Instant.now());
        account.setBalance(account.getBalance() - value);
        accountRepository.save(account);
    }

    private AccountEntity createNewAccount(String id) {
        AccountEntity account = new AccountEntity();
        account.setOwnerId(id);
        account.setBalance(0L);
        return accountRepository.save(account);
    }
}
