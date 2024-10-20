package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.avelichko.NauJava.model.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    
}
