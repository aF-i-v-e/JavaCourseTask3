package ru.avelichko.NauJava.webController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.avelichko.NauJava.exception.ServiceException;
import ru.avelichko.NauJava.model.ExpenseCategory;
import ru.avelichko.NauJava.model.IncomeCategory;
import ru.avelichko.NauJava.service.ExpenseCategoryService;
import ru.avelichko.NauJava.service.IncomeCategoryService;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @Autowired
    private IncomeCategoryService incomeCategoryService;

    @RequestMapping("/category/list")
    public String getCategoryList(Model model) {
        List<ExpenseCategory> expenseCategories = expenseCategoryService.getExpenseCategories();
        List<IncomeCategory> incomeCategories = incomeCategoryService.getIncomeCategories();
        model.addAttribute("expenseCategories", expenseCategories);
        model.addAttribute("incomeCategories", incomeCategories);
        return "categories-list";
    }

    @RequestMapping("/category/expenses/add")
    public String addExpenseCategory(@Valid ExpenseCategory expenseCategory, Model model) {
        try {
            expenseCategoryService.addExpenseCategory(expenseCategory);
        } catch (ServiceException exception) {
            model.addAttribute("expenseCategoryMessage", exception.getMessage());
        }
        return getCategoryList(model);
    }

    @RequestMapping("/category/expenses/delete/{id}")
    public String deleteExpenseCategory(@PathVariable("id") long id, Model model) {
        expenseCategoryService.deleteExpenseCategoryById(id);
        return getCategoryList(model);
    }

    @RequestMapping("/category/incomes/delete/{id}")
    public String deleteIncomeCategory(@PathVariable("id") long id, Model model) {
        incomeCategoryService.deleteIncomeCategoryById(id);
        return getCategoryList(model);
    }

    @RequestMapping("/category/incomes/add")
    public String addIncomeCategory(@Valid IncomeCategory incomeCategory, Model model) {
        try {
            incomeCategoryService.addIncomeCategory(incomeCategory);
        } catch (ServiceException exception) {
            model.addAttribute("incomeCategoryMessage", exception.getMessage());
        }
        return getCategoryList(model);
    }
}
