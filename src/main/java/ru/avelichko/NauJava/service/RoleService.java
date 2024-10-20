package ru.avelichko.NauJava.service;

public interface RoleService {
    /**
     * Удаляет роль по названию
     * @param roleTitle название роли
     */
    void deleteRole(String roleTitle);
}
