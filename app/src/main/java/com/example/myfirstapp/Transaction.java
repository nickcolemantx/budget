package com.example.myfirstapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction {
    private String description;
    private String amount;
    private String assigned;
    private String type;
    private Calendar date;

    private final String sep = " | ";

    public Transaction(){}

    public Transaction(String description, String amount, String assigned, String type, Calendar date)
    {
        this.description = description;
        this.amount = amount;
        this.assigned = assigned;
        this.type = type;
        this.date = date;
    }

    public Calendar getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public String getAssigned() {
        return assigned;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(date.getTime()) + sep
                + description + sep
                + amount + sep
                + type + sep
                + assigned + sep;
    }
}
