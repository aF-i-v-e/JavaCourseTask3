package ru.avelichko.NauJava.webController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.avelichko.NauJava.model.Account;
import ru.avelichko.NauJava.model.AccountReport;
import ru.avelichko.NauJava.model.ExpenseCategory;
import ru.avelichko.NauJava.model.IncomeCategory;
import ru.avelichko.NauJava.service.AccountReportService;
import ru.avelichko.NauJava.service.AccountService;
import ru.avelichko.NauJava.service.ExpenseCategoryService;
import ru.avelichko.NauJava.service.IncomeCategoryService;

import java.util.List;

@Controller
public class AccountReportController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountReportService accountReportService;

    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @Autowired
    private IncomeCategoryService incomeCategoryService;

    private final String successReport = "Отчёт успешно сформирован!";
    private final String notSuccessReport = "Ошибка формирования отчёта!";

    @GetMapping("/report/expense/all")
    public String getAllExpenseReport(Model model) {
        return "report-expense-all";
    }

    @GetMapping("/report/income/all")
    public String getAllIncomeReport(Model model) {
        return "report-income-all";
    }

    @RequestMapping("/report/expense/all/create")
    public String createAllExpenseReport(@Valid AccountReport accountReport, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccountByLogin(auth.getName());

        Boolean isSuccess = accountReportService.fillAllExpenseReport(account, accountReport);
        String message = isSuccess ? successReport : notSuccessReport;

        model.addAttribute("message", message);
        model.addAttribute("generatedReport", accountReport);
        return getAllExpenseReport(model);
    }

    @RequestMapping("/report/income/all/create")
    public String createAllIncomeReport(@Valid AccountReport accountReport, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccountByLogin(auth.getName());

        Boolean isSuccess = accountReportService.fillAllIncomeReport(account, accountReport);
        String message = isSuccess ? successReport : notSuccessReport;

        model.addAttribute("message", message);
        model.addAttribute("generatedReport", accountReport);
        return getAllIncomeReport(model);
    }

    @GetMapping("/report/expense/specific")
    public String getSpecificExpenseReport(Model model) {
        List<ExpenseCategory> expenseCategories = expenseCategoryService.getExpenseCategories();
        model.addAttribute("expenseCategories", expenseCategories);
        return "report-expense-specific";
    }

    @GetMapping("/report/income/specific")
    public String getSpecificIncomeReport(Model model) {
        List<IncomeCategory> incomeCategories = incomeCategoryService.getIncomeCategories();
        model.addAttribute("incomeCategories", incomeCategories);
        return "report-income-specific";
    }

    @RequestMapping("/report/expense/specific/create")
    public String createSpecificExpenseReport(@Valid ExpenseCategory expenseCategory, @Valid AccountReport accountReport, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccountByLogin(auth.getName());

        Boolean isSuccess = accountReportService.fillSpecificExpenseReport(account, accountReport, expenseCategory);
        String message = isSuccess ? successReport : notSuccessReport;

        model.addAttribute("message", message);
        model.addAttribute("generatedReport", accountReport);
        return getSpecificExpenseReport(model);
    }

    @RequestMapping("/report/income/specific/create")
    public String createSpecificIncomeReport(@Valid IncomeCategory incomeCategory,
                                             @Valid AccountReport accountReport,
                                             Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccountByLogin(auth.getName());

        Boolean isSuccess = accountReportService.fillSpecificIncomeReport(account, accountReport, incomeCategory);
        String message = isSuccess ? successReport : notSuccessReport;

        model.addAttribute("message", message);
        model.addAttribute("generatedReport", accountReport);
        return getSpecificIncomeReport(model);
    }
}
