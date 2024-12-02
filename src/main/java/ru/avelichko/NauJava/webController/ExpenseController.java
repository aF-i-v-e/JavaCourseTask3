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
import ru.avelichko.NauJava.model.Expense;
import ru.avelichko.NauJava.model.ExpenseCategory;
import ru.avelichko.NauJava.service.AccountService;
import ru.avelichko.NauJava.service.ExpenseCategoryService;
import ru.avelichko.NauJava.service.ExpenseService;

import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @RequestMapping("/expense")
    public String expense(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccountByLogin(auth.getName());

        List<Expense> expenses = account.getExpenses();
        List<ExpenseCategory> expenseCategories = expenseCategoryService.getExpenseCategories();
        model.addAttribute("expenses", expenses);
        model.addAttribute("expenseCategories", expenseCategories);
        return "expense";
    }

    @RequestMapping("/expense/add")
    public String createExpense(@Valid Expense expense, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccountByLogin(auth.getName());
        expenseService.createExpense(expense, account);
        return expense(model);
    }

    @RequestMapping("/expense/delete/{id}")
    public String deleteExpense(@PathVariable("id") long id, Model model) {
        expenseService.deleteExpenseById(id);
        return expense(model);
    }
}
