package ru.avelichko.NauJava.webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avelichko.NauJava.model.AccountReport;
import ru.avelichko.NauJava.service.AccountReportService;
import ru.avelichko.NauJava.service.SaveService;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class SaveController {
    @Autowired
    private SaveService saveService;

    @Autowired
    private AccountReportService accountReportService;

    private final Path savePath = Paths.get(System.getProperty("user.dir"))
            .resolve("src").resolve("main").resolve("resources")
            .resolve("accountReports").resolve("save");

    private final String notFoundReport = "Отчёт не был найден!";

    @RequestMapping("/report/save/{id}")
    public String saveReport(@PathVariable("id") long id, Model model) {
        AccountReport accountReport = accountReportService.findByAccountReportId(id);
        if (accountReport == null)
            return notFoundReport;
        Path dirPath = savePath.resolve(accountReport.getAccount().getAccountUser().getLogin());
        return saveService.save(dirPath, accountReport);
    }
}