package ru.avelichko.NauJava.service;

import ru.avelichko.NauJava.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findByTitle(String roleTitle);

    /**
     * Удаляет роль по названию
     *
     * @param roleTitle название роли
     */
    void deleteRole(String roleTitle);
}
