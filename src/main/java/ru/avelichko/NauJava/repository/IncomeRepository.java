package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.avelichko.NauJava.model.Income;

@RepositoryRestResource(path = "incomes")
public interface IncomeRepository extends CrudRepository<Income, Long> {

}
