package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.avelichko.NauJava.model.IncomeCategory;

public interface IncomeCategoryRepository extends CrudRepository<IncomeCategory, Long> {
    
}
