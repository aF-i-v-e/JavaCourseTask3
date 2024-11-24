package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.avelichko.NauJava.model.IncomeCategory;

import java.util.List;

@RepositoryRestResource(path = "income_categories")
public interface IncomeCategoryRepository extends CrudRepository<IncomeCategory, Long> {
    List<IncomeCategory> findByIncomeCategoryName(String incomeCategoryName);
}
