package com.example.bank.exceptions;


public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
        super();
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
