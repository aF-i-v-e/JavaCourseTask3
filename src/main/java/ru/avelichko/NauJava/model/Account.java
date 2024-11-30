package ru.avelichko.NauJava.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long accountId;

    @OneToOne(mappedBy = "account")
    private AccountUser accountUser;

    @OneToMany(mappedBy = "account")
    private List<Expense> expenses;

    @OneToMany(mappedBy = "account")
    private List<Income> incomes;

    @OneToMany(mappedBy = "account")
    private List<AccountReport> accountReports;

    @OneToMany(mappedBy = "account")
    private List<Report> reports;

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }

    public List<AccountReport> getAccountReports() {
        return accountReports;
    }

    public void setAccountReports(List<AccountReport> accountReports) {
        this.accountReports = accountReports;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
