package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.avelichko.NauJava.model.Account;
import ru.avelichko.NauJava.model.AccountReport;

import java.util.List;

@RepositoryRestResource(path = "account_reports")
public interface AccountReportRepository extends CrudRepository<AccountReport, Long> {
    List<AccountReport> findByAccount(Account account);
}
