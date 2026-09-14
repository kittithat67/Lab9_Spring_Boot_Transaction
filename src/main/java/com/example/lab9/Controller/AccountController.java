package com.example.lab9.Controller;

import java.util.Map;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab9.Service.AccountService;
import com.example.lab9.Service.DepositService;
import com.example.lab9.model.Account;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final DepositService depositService;

    public  AccountController(AccountService accountService,DepositService depositService){
        this.accountService = accountService;
        this.depositService = depositService;
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable long id) {
        return accountService.getAccount(id);
    }

    @PostMapping("/{id}/deposit")
    public Map<String, String> deposit(@PathVariable long id, @RequestBody DepositRequest request) {
        depositService.deposit(id, request.amount);
        return Map.of("message", "Deposit successful");
    }

    public static class DepositRequest {
        public Double amount;
    }

}

