package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.avelichko.NauJava.model.ExpenseCategory;

@RepositoryRestResource(path = "expense_categories")
public interface ExpenseCategoryRepository extends CrudRepository<ExpenseCategory, Long> {

}
