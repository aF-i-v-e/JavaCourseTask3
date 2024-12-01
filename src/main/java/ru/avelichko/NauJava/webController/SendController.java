package ru.avelichko.NauJava.webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.avelichko.NauJava.model.AccountReport;
import ru.avelichko.NauJava.service.AccountReportService;
import ru.avelichko.NauJava.service.SaveService;
import ru.avelichko.NauJava.service.SendEmailService;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class SendController {
    @Autowired
    private SaveService saveService;

    @Autowired
    private AccountReportService accountReportService;

    @Autowired
    private SendEmailService sendEmailService;

    private final String notFoundReport = "Отчёт не был найден!";
    private final Path savePath = Paths.get(System.getProperty("user.dir"))
            .resolve("src").resolve("main").resolve("resources")
            .resolve("accountReports").resolve("send");

    @RequestMapping("/report/send/{id}")
    public String sendReport(@RequestParam("recipient") String recipient, @PathVariable("id") long id, Model model) {
        AccountReport accountReport = accountReportService.findByAccountReportId(id);
        if (accountReport == null)
            return notFoundReport;
        Path dirPath = savePath.resolve(accountReport.getAccount().getAccountUser().getLogin());
        saveService.save(dirPath, accountReport);

        String subject = String.format("Отчёт №%d", accountReport.getAccountReportId());
        Path pdfPath = saveService.getAccountReportPath(dirPath, accountReport);
        return sendEmailService.sendEmail(recipient, subject, pdfPath.toString());
    }
}
