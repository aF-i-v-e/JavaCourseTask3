package ru.avelichko.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.avelichko.NauJava.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    /**
     * Удаляет роль с заданным названием
     */
    void deleteByTitle(String roleTitle);
}
