package ru.avelichko.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.avelichko.NauJava.domain.ReportStatusEnum;
import ru.avelichko.NauJava.model.Report;
import ru.avelichko.NauJava.repository.ReportRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class ReportService {
    private final ReportRepository reportRepository;
    private final AccountUserService accountUserService;
    private final ExpenseCategoryService expenseCategoryService;

    @Autowired
    ReportService(ReportRepository reportRepository,
                  AccountUserService accountUserService,
                  ExpenseCategoryService expenseCategoryService) {
        this.reportRepository = reportRepository;
        this.accountUserService = accountUserService;
        this.expenseCategoryService = expenseCategoryService;
    }

    public String getReportContent(Long id) {
        Optional<Report> report = reportRepository.findById(id);
        if (report.isPresent()) {
            return report.get().getContent();
        }
        return "";
    }

    public Report generateReportAsync() {
        long startTime = System.currentTimeMillis();

        Report report = new Report();
        report.setStatus(ReportStatusEnum.CREATED.toString());
        saveReport(report);

        CompletableFuture<Report> future = CompletableFuture.supplyAsync(() -> {
            try {
                fillReport(report);
                long reportTime = System.currentTimeMillis() - startTime;
                report.setReportTime(reportTime);
                report.setStatus(ReportStatusEnum.COMPLETED.toString());
                saveReport(report);
            } catch (Exception e) {
                System.out.println("При формировании отчёта произошла ошибка: " + e.getMessage());
                report.setStatus(ReportStatusEnum.ERROR.toString());
                saveReport(report);
            }
            return report;
        });

        future.thenAccept(futureResult -> System.out.println("Отчёт сформирован!"));
        return report;
    }

    public Long saveReport(Report report) {
        reportRepository.save(report);
        return report.getReportId();
    }

    public String getReportContentById(Long id) {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            if (report.getStatus().equals(ReportStatusEnum.CREATED.toString())) {
                return String.format("Отчёт № %s ещё не сформирован!", id);
            }
            if (Objects.equals(report.getStatus(), ReportStatusEnum.ERROR.toString())) {
                return String.format("Отчёт № %s сформирован с ошибкой!", id);
            }
            return report.getContent();
        }
        return String.format("Отсутствует отчёт № %s", id);
    }

    public Report getReportById(Long id) {
        return reportRepository.findById(id).orElse(null);
    }

    private void fillReport(Report report) throws InterruptedException {
        AtomicInteger accountUserCount = new AtomicInteger();
        Thread threadCountUser = new Thread(() -> {
            System.out.println("Запущен поток по расчёту количества пользователей.");
            accountUserCount.set(accountUserService.countAccountUsers());
        });

        AtomicReference<String> content = new AtomicReference<>();
        Thread threadGetContent = new Thread(() -> {
            System.out.println("Запущен поток по получению содержимого.");
            content.set(expenseCategoryService.getExpenseCategoriesContent());
        });

        long startTime = System.currentTimeMillis();

        threadCountUser.start();
        threadGetContent.start();

        threadCountUser.join();
        long accountUserCountTime = System.currentTimeMillis() - startTime;

        threadGetContent.join();
        long contentTime = System.currentTimeMillis() - startTime;

        report.setAccountUserCount(accountUserCount.get());
        report.setAccountUserCountTime(accountUserCountTime);
        report.setContent(content.get());
        report.setContentTime(contentTime);
    }
}
