package ru.avelichko.NauJava.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "account_report")
public class AccountReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_report_id")
    private Long accountReportId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "date_start")
    private LocalDateTime dateStart;

    @Column(name = "date_end")
    private LocalDateTime dateEnd;

    @Column(name = "total_sum")
    private Double totalSum;

    // информация о категории:
    // конкретная категория доходов / расходов;
    // по всем категориям доходов / расходов;
    @Column(name = "category_info")
    private String categoryInfo;

    // Дополнительная информация об отчёте
    @Column(name = "dop_info")
    private String dopInfo;

    public Long getAccountReportId() {
        return accountReportId;
    }

    public void setAccountReportId(Long accountReportId) {
        this.accountReportId = accountReportId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public String getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(String categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public String getDopInfo() {
        return dopInfo;
    }

    public void setDopInfo(String dopInfo) {
        this.dopInfo = dopInfo;
    }
}
