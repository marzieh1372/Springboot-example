package com.example.bank.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank/account")
public class BankController {

    @GetMapping("/{accountId}/balance")
    public void getBalance(@PathVariable("accountId") long accountId){}

    @PostMapping("/{accountId}/deposit")
    public void depositToAccount(@PathVariable("accountId") long accountId){}

    @PostMapping("/{accountId}/withdrawal")
    public void withdrawal(@PathVariable("accountId") long accountId){}

}
