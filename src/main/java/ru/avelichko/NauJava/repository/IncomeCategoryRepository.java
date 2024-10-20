package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.avelichko.NauJava.model.IncomeCategory;

@RepositoryRestResource(path = "income_categories")
public interface IncomeCategoryRepository extends CrudRepository<IncomeCategory, Long> {

}
