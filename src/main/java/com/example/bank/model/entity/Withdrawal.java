package com.example.bank.model.entity;


public class Withdrawal {
    private double amount;

    public Withdrawal() {
    }

    public Withdrawal(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
