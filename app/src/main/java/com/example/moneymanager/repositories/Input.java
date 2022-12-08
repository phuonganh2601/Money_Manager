package com.example.moneymanager.repositories;


import java.io.Serializable;

public class Input implements Serializable {
    private long amount, type, date, month, year;
    private String note = "", category = "", currency = "";

    public Input(int amount, String currency, String note, String category, int type) {
        this.amount = amount;
        this.currency = currency;
        this.note = note;
        this.category = category;
        this.type = type;

    }

    public Input(int amount, String currency, String category, int type) {
        this.amount = amount;
        this.currency = currency;
        this.category = category;
        this.type = type;
    }

    public long getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public String getCategory() {
        return category;
    }

    public long getType() {
        return type;
    }

    public long getDate() {
        return date;
    }

    public long getMonth() {
        return month;
    }

    public long getYear() {
        return year;
    }

    public String getCurrency() {
        return currency;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setMonth(long month) {
        this.month = month;
    }

    public void setYear(long year) {
        this.year = year;
    }


    @Override
    public String toString() {
        return String.format("%d %s %s", amount, note, category);
    }
}
