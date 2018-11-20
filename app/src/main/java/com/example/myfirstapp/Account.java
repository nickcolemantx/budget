package com.example.myfirstapp;

import java.util.List;

public class Account {

    private String num;
    List<Transaction> transactions;

    public Account() {}

    public Account(String num, List<Transaction> trans){
        this.num = num;
        this.transactions = trans;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString(){
        return num + " : " + transactions.toString();
    }
}

