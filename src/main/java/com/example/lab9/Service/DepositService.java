package com.example.lab9.Service;

import org.springframework.stereotype.Service;

import com.example.lab9.model.Account;
import com.example.lab9.model.DepositTransaction;
import com.example.lab9.repository.AccountRepository;
import com.example.lab9.repository.DepositRepository;

import jakarta.transaction.Transactional;

@Service
public class DepositService {
    private AccountRepository accountRepository;
    private DepositRepository depositRepository;

    public DepositService(AccountRepository accountRepository,DepositRepository depositRepository){
        this.accountRepository = accountRepository;
        this.depositRepository = depositRepository;
    }

    //@Transactional
    public void deposit(Long accountId,Double amount){
        Account account = accountRepository.findById(accountId).orElseThrow();
        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);
        DepositTransaction depositTransaction = new DepositTransaction();
        depositTransaction.setAccount(account);
        depositTransaction.setAmount(amount);
        depositRepository.save(depositTransaction);

        throw new RuntimeException("Test Rollback");
        
    }
}
