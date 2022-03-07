package com.example.bank.model.entity;


public class Deposit {
    private double amount;

    public Deposit() {
    }

    public Deposit(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
