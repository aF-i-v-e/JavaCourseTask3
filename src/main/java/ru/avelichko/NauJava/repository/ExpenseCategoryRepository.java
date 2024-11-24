package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.avelichko.NauJava.model.ExpenseCategory;

import java.util.List;

@RepositoryRestResource(path = "expense_categories")
public interface ExpenseCategoryRepository extends CrudRepository<ExpenseCategory, Long> {
    List<ExpenseCategory> findByExpenseCategoryName(String expenseCategoryName);
}
