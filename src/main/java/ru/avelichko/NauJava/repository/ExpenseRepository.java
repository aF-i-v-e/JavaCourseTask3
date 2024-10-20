package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.avelichko.NauJava.model.Expense;

@RepositoryRestResource(path = "expenses")
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

}
