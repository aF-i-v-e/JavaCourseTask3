package ru.avelichko.NauJava.webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.avelichko.NauJava.model.Report;
import ru.avelichko.NauJava.service.ReportService;

@Controller
@RequestMapping("/view")
public class ReportControllerView {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public String generateReport(Model model) {
        Report report = reportService.generateReportAsync();
        model.addAttribute("report", report);
        return "report";
    }

    @GetMapping("/report/{id}")
    public String getReportById(@PathVariable("id") long id, Model model) {
        String content = reportService.getReportContent(id);
        model.addAttribute("message", content);
        return "report";
    }
}
