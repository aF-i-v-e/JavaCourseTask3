package ru.avelichko.NauJava.webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avelichko.NauJava.model.AccountReport;
import ru.avelichko.NauJava.service.AccountReportService;
import ru.avelichko.NauJava.service.SaveService;

import java.io.IOException;
import java.nio.file.Files;
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
            .resolve("accountReports");
    private final String successfulMessage = "Отчёт успешно сохранён в ";
    private final String notFoundReport = "Отчёт не был найден!";
    private final String unsuccessfulMessage = "Ошибка при сохранении отчёта в ";

    @RequestMapping("/report/save/{id}")
    public String saveReport(@PathVariable("id") long id, Model model) throws IOException {
        AccountReport accountReport = accountReportService.findByAccountReportId(id);
        if (accountReport == null)
            return notFoundReport;
        Path dirPath = savePath.resolve(accountReport.getAccount().getAccountUser().getLogin());
        return save(dirPath, accountReport);
    }

    private String save(Path dirPath, AccountReport accountReport) {
        String accountReportSavePath = dirPath
                .resolve(accountReport.getAccountReportId().toString() + ".pdf")
                .toString();
        if (!Files.exists(dirPath)) {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return unsuccessfulMessage + accountReportSavePath;
            }
        }
        Boolean isSuccess = saveService.saveToPdf(accountReport, accountReportSavePath);
        String message = isSuccess ? successfulMessage : unsuccessfulMessage;
        message += accountReportSavePath;
        return message;
    }
}