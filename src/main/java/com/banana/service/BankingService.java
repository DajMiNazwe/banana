package com.banana.service;

import com.banana.model.AccountEntity;
import com.banana.model.DecreaseRequest;
import com.banana.model.TransactionDTO;
import com.banana.model.TransactionEntity;
import com.banana.model.TransactionEntity.TransactionType;
import com.banana.repository.AccountRepository;
import com.banana.repository.TransactionRepository;
import com.banana.shared.AccountNotFoundException;
import com.banana.shared.InvalidTokenException;
import com.banana.shared.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankingService {

    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    private final TokenService tokenService;

    private final TransactionMapper transactionMapper;

    @Autowired
    public BankingService(TransactionRepository transactionRepository,
                          AccountRepository accountRepository,
                          TokenService tokenService,
                          TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.tokenService = tokenService;
        this.transactionMapper = transactionMapper;
    }

    public Double getBalance(String id) {
        Optional<AccountEntity> account = accountRepository.findOneByOwnerId(id);
        if (account.isPresent()) {
            return account.get().getBalance();
        } else {
            throw new AccountNotFoundException(id);
        }
    }

    public List<TransactionDTO> getTransactionHistory(String id) {
        return transactionRepository.findAllByOwnerId(id).stream().map(transactionMapper::map).collect(Collectors.toList());
    }

    @Transactional
    public void increaseFunds(Double value, String id) {
        Optional<AccountEntity> optionalAccount = accountRepository.findOneByOwnerId(id);
        AccountEntity account = optionalAccount.orElseGet(() -> createNewAccount(id));
        addNewTransaction(TransactionType.INCREASE, id, value);
        account.setBalance(account.getBalance() + value);
        accountRepository.save(account);
    }

    @Transactional
    public void decreaseFunds(DecreaseRequest request, String id) {
        if (!tokenService.isTokenValid(request.getToken(), id)) {
            throw new InvalidTokenException(request.getToken());
        }
        Optional<AccountEntity> optionalAccount = accountRepository.findOneByOwnerId(id);
        AccountEntity account = optionalAccount.orElseThrow(() -> new AccountNotFoundException(id));
        addNewTransaction(TransactionType.DECREASE, id, request.getValue());
        account.setBalance(account.getBalance() - request.getValue());
        accountRepository.save(account);
    }

    private AccountEntity createNewAccount(String id) {
        AccountEntity account = new AccountEntity();
        account.setOwnerId(id);
        account.setBalance(0.00);
        return accountRepository.save(account);
    }

    private void addNewTransaction(TransactionType type, String ownerId, Double value) {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setOwnerId(ownerId);
        transaction.setType(type);
        transaction.setValue(value);
        transaction.setOperationDate(Instant.now());
        transactionRepository.save(transaction);
    }
}
