package com.project.BankAccountsManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class Accounts {
    @Id
    private String accountNumber;
    private String bankName;
    private double amount;
    private double minBalance;

    public String toString(){
        return "Account Number: "+accountNumber+" \tamount: "+amount;
    }

    public Accounts() {
        System.out.println("Default constructor");
    }

    public Accounts(String accountNumber, String bankName, double amount, double minBalance) {
        System.out.println("Paramaterized constructor");
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.amount = amount;
        this.minBalance = minBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }
}
