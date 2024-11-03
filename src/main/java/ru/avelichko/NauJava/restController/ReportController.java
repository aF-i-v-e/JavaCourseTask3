package ru.avelichko.NauJava.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avelichko.NauJava.service.ReportService;

@RestController
@RequestMapping("/rest")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public Long generateReport(Model model) {
        return reportService.generateReportAsync().getReportId();
    }

    @GetMapping("/report/{id}")
    public String getReportById(@PathVariable("id") long id, Model model) {
        return reportService.getReportContent(id);
    }
}
