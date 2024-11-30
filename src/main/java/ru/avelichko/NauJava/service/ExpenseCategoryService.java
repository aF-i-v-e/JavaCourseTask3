package ru.avelichko.NauJava.service;

import org.springframework.stereotype.Component;
import ru.avelichko.NauJava.domain.ExpenseCategoryEnum;
import ru.avelichko.NauJava.exception.ServiceException;
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
        List<ExpenseCategory> expenseCategories = getExpenseCategories();
        StringBuilder content = new StringBuilder();
        content.append("\nId\tНазвание\tКоличество записей\n");
        expenseCategories.forEach(expenseCategory ->
                content.append(String.format("%s\t%s\t%s\n",
                        expenseCategory.getExpenseCategoryId(),
                        expenseCategory.getExpenseCategoryName(),
                        expenseCategory.getExpenses().size())));
        return content.toString();
    }

    public ArrayList<ExpenseCategory> getExpenseCategories() {
        ArrayList<ExpenseCategory> expenseCategories = new ArrayList<>();
        expenseCategoryRepository.findAll().forEach(expenseCategories::add);
        if (expenseCategories.isEmpty()) {
            addDefaultExpenseCategories(expenseCategories);
        }
        return expenseCategories;
    }

    public void deleteExpenseCategoryById(Long id) {
        expenseCategoryRepository.deleteById(id);
    }

    public void addExpenseCategory(ExpenseCategory expenseCategory) throws ServiceException {
        List<ExpenseCategory> expenseCategories = expenseCategoryRepository
                .findByExpenseCategoryName(expenseCategory.getExpenseCategoryName());
        if (expenseCategories.isEmpty()) {
            expenseCategoryRepository.save(expenseCategory);
        } else {
            throw new ServiceException(String.format(
                    "Expense category with name %s already exists!",
                    expenseCategory.getExpenseCategoryName()
            ));
        }
    }

    private void addDefaultExpenseCategories(List<ExpenseCategory> expenseCategories) {
        List<ExpenseCategoryEnum> expenseCategoryEnum = List.of(
                ExpenseCategoryEnum.FOOD,
                ExpenseCategoryEnum.TRANSPORT,
                ExpenseCategoryEnum.HEALTH,
                ExpenseCategoryEnum.HOUSING,
                ExpenseCategoryEnum.HOBBY,
                ExpenseCategoryEnum.CLOTHING,
                ExpenseCategoryEnum.ENTERTAINMENTS,
                ExpenseCategoryEnum.OTHER
        );
        for (ExpenseCategoryEnum expenseCategoryType : expenseCategoryEnum) {
            ExpenseCategory expenseCategory = new ExpenseCategory(expenseCategoryType.getTitle(), Collections.emptyList());
            expenseCategoryRepository.save(expenseCategory);
            expenseCategories.add(expenseCategory);
        }
    }
}
