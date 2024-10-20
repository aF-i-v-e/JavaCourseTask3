package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.avelichko.NauJava.model.ExpenseCategory;

public interface ExpenseCategoryRepository extends CrudRepository<ExpenseCategory, Long> {
    
}
