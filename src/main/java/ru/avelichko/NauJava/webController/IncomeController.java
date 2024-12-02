package ru.avelichko.NauJava.webController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.avelichko.NauJava.model.Account;
import ru.avelichko.NauJava.model.Income;
import ru.avelichko.NauJava.model.IncomeCategory;
import ru.avelichko.NauJava.service.AccountService;
import ru.avelichko.NauJava.service.IncomeCategoryService;
import ru.avelichko.NauJava.service.IncomeService;

import java.util.List;

@Controller
public class IncomeController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private IncomeCategoryService incomeCategoryService;

    @RequestMapping("/income")
    public String income(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccountByLogin(auth.getName());

        List<Income> incomes = account.getIncomes();
        List<IncomeCategory> incomesCategories = incomeCategoryService.getIncomeCategories();
        model.addAttribute("incomes", incomes);
        model.addAttribute("incomeCategories", incomesCategories);
        return "income";
    }

    @RequestMapping("/income/add")
    public String createIncome(@Valid Income income, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccountByLogin(auth.getName());
        incomeService.createIncome(income, account);
        return income(model);
    }

    @RequestMapping("/income/delete/{id}")
    public String deleteIncome(@PathVariable("id") long id, Model model) {
        incomeService.deleteIncomeById(id);
        return income(model);
    }
}
