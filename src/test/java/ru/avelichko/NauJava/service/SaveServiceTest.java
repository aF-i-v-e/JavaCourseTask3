package ru.avelichko.NauJava.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.avelichko.NauJava.model.Account;
import ru.avelichko.NauJava.model.AccountReport;

import java.nio.file.Paths;
import java.time.LocalDateTime;

@SpringBootTest
public class SaveServiceTest {
    private final SaveService saveService;

    @Autowired
    public SaveServiceTest(SaveService saveService) {
        this.saveService = saveService;
    }

    @Test
    void successfullySavePdf() {
        AccountReport accountReport = new AccountReport();
        accountReport.setAccountReportId(1L);
        accountReport.setAccount(new Account());
        accountReport.setTotalSum(1000.00);
        accountReport.setDateStart(LocalDateTime.now());
        accountReport.setDateEnd(LocalDateTime.now());
        accountReport.setCategoryInfo("Тестовый отчет");
        accountReport.setDopInfo("Дополнительная информация");
        String path = Paths.get(System.getProperty("user.dir"))
                .resolve("src").resolve("test").resolve("java")
                .resolve("ru").resolve("avelichko")
                .resolve("NauJava").resolve("resources")
                .resolve("test.pdf").toString();
        saveService.saveToPdf(accountReport, path);
    }
}
