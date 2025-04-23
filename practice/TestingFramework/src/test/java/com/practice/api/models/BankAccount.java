package com.practice.api.models;

public class BankAccount {

    private Integer id;
    private String name;
    private double balance;

    private BankAccount(BankAccountBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.balance = builder.balance;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("BankAccount{id=%d, name='%s', balance=%.2f}", id, name, balance);
    }

    public static class BankAccountBuilder {
        private Integer id;
        private String name;
        private double balance;

        public BankAccountBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public BankAccountBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public BankAccountBuilder setBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public BankAccount build() {
            return new BankAccount(this);
        }
    }
}