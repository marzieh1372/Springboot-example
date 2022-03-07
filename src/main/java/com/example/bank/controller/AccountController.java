package com.example.bank.controller;

import com.example.bank.model.entity.Account;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @PutMapping
    public void updateAccount(Account account){}

    @PostMapping
    public void addAccount(@RequestBody Account account){}

    @GetMapping("/{accountId}")
    public void getAccountById(@PathVariable("accountId") Long accountId){}

    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable("accountId") Long accountId){}
}
