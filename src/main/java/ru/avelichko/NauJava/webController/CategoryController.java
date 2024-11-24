package ru.avelichko.NauJava.webController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.avelichko.NauJava.exception.ServiceException;
import ru.avelichko.NauJava.model.ExpenseCategory;
import ru.avelichko.NauJava.service.ExpenseCategoryService;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @RequestMapping("/category/list")
    public String getCategoryList(Model model) {
        List<ExpenseCategory> expenseCategories = expenseCategoryService.getExpenseCategories();
        model.addAttribute("expenseCategories", expenseCategories);
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
}
