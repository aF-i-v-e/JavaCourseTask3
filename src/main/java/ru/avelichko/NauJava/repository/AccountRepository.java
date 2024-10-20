package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.avelichko.NauJava.model.Account;

@RepositoryRestResource(path = "accounts")
public interface AccountRepository extends CrudRepository<Account, Long> {
}
