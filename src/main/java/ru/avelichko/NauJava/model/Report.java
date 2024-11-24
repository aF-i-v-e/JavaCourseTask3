package ru.avelichko.NauJava.model;

import jakarta.persistence.*;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "status")
    private String status;

    @Column(name = "account_user_count")
    private Integer accountUserCount;

    @Column(name = "account_user_count_time")
    private Long accountUserCountTime;

    @Column(name = "content")
    private String content;

    @Column(name = "content_time")
    private Long contentTime;

    @Column(name = "report_time")
    private Long reportTime;

    public Report() {

    }

    public Report(int accountUserCount, long accountUserCountTime,
                  String content, long contentTime, long reportTime) {
        this.accountUserCount = accountUserCount;
        this.accountUserCountTime = accountUserCountTime;
        this.content = content;
        this.contentTime = contentTime;
        this.reportTime = reportTime;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAccountUserCount() {
        return accountUserCount;
    }

    public void setAccountUserCount(Integer accountUserCount) {
        this.accountUserCount = accountUserCount;
    }

    public Long getAccountUserCountTime() {
        return accountUserCountTime;
    }

    public void setAccountUserCountTime(Long accountUserCountTime) {
        this.accountUserCountTime = accountUserCountTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getContentTime() {
        return contentTime;
    }

    public void setContentTime(long contentTime) {
        this.contentTime = contentTime;
    }

    public Long getReportTime() {
        return reportTime;
    }

    public void setReportTime(long reportTime) {
        this.reportTime = reportTime;
    }
}
