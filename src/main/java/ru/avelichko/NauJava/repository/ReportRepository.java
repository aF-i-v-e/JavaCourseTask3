package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.avelichko.NauJava.model.Report;

@RepositoryRestResource(path = "reports")
public interface ReportRepository extends CrudRepository<Report, Long> {
}
