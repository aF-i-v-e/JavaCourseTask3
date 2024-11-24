package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.avelichko.NauJava.model.Role;

import java.util.List;

@RepositoryRestResource(path = "roles")
public interface RoleRepository extends CrudRepository<Role, Long> {
    /**
     * Получить роль по названию
     */
    List<Role> findByTitle(String roleTitle);

    /**
     * Удаляет роль с заданным названием
     */
    void deleteByTitle(String roleTitle);
}
