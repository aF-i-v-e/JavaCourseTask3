package ru.avelichko.NauJava.model;

import java.util.Date;

public class Account {
    private Long id;

    private AccountCategory accountCategory;

    private Double amount;

    private Date date;

    public Account(Long id, AccountCategory accountCategory, Double amount, Date date) {
        this.id = id;
        this.accountCategory = accountCategory;
        this.amount = amount;
        this.date = date;
    }

    public Account() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountCategory getAccountCategory() {
        return accountCategory;
    }

    public void setAccountCategory(AccountCategory accountCategory) {
        this.accountCategory = accountCategory;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
