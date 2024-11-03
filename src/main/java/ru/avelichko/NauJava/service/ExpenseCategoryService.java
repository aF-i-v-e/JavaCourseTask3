package ru.avelichko.NauJava.service;

import org.springframework.stereotype.Component;
import ru.avelichko.NauJava.domain.ExpenseCategoryEnum;
import ru.avelichko.NauJava.model.ExpenseCategory;
import ru.avelichko.NauJava.repository.ExpenseCategoryRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ExpenseCategoryService {
    private final ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategoryService(ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    public String getExpenseCategoriesContent() {
        ArrayList<ExpenseCategory> expenseCategories = getExpenseCategories();
        if (expenseCategories.isEmpty()) {
            saveExpenseCategories();
            expenseCategories = getExpenseCategories();
        }
        StringBuilder content = new StringBuilder();
        content.append("\nId\tНазвание\tКоличество записей\n");
        expenseCategories.forEach(expenseCategory ->
                content.append(String.format("%s\t%s\t%s\n",
                        expenseCategory.getExpenseCategoryId(),
                        expenseCategory.getExpenseCategoryName(),
                        expenseCategory.getExpenses().size())));
        return content.toString();
    }

    private ArrayList<ExpenseCategory> getExpenseCategories() {
        ArrayList<ExpenseCategory> expenseCategories = new ArrayList<>();
        expenseCategoryRepository.findAll().forEach(expenseCategories::add);
        return expenseCategories;
    }

    private void saveExpenseCategories() {
        List<ExpenseCategoryEnum> expenseCategoryEnum = List.of(ExpenseCategoryEnum.FOOD,
                ExpenseCategoryEnum.TRANSPORT, ExpenseCategoryEnum.HEALTH, ExpenseCategoryEnum.HEALTH,
                ExpenseCategoryEnum.HOUSING, ExpenseCategoryEnum.HOBBY, ExpenseCategoryEnum.CLOTHING,
                ExpenseCategoryEnum.ENTERTAINMENTS, ExpenseCategoryEnum.OTHER);
        for (ExpenseCategoryEnum expenseCategoryType : expenseCategoryEnum) {
            ExpenseCategory expenseCategory = new ExpenseCategory(expenseCategoryType.getTitle(), Collections.emptyList());
            expenseCategoryRepository.save(expenseCategory);
        }
    }
}
