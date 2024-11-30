package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.avelichko.NauJava.model.AccountReport;

@RepositoryRestResource(path = "account_reports")
public interface AccountReportRepository extends CrudRepository<AccountReport, Long> {
}
