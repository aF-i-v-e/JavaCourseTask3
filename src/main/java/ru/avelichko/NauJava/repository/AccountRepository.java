package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.avelichko.NauJava.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
