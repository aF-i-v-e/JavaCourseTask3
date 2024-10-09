package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.avelichko.NauJava.model.Income;

public interface IncomeRepository extends CrudRepository<Income, Long> {
    
}
