package ru.avelichko.NauJava.model;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Доходы пользователя
 */
@Entity
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "income_id")
    private Long incomeId;

    @ManyToOne
    @JoinColumn(name = "income_category_id")
    private IncomeCategory incomeCategory;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private Date date;

    public void setAccount(Account account) {
        this.account = account;
    }

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public void setIncomeId(Long incomeId) {
        this.incomeId = incomeId;
    }

    public IncomeCategory getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(IncomeCategory incomeCategory) {
        this.incomeCategory = incomeCategory;
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
    
    public Long getIncomeId() {
        return incomeId;
    }

    public Account getAccount() {
        return account;
    }
}
