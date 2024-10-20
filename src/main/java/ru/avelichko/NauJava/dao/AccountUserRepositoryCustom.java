package ru.avelichko.NauJava.dao;

import java.util.List;
import ru.avelichko.NauJava.model.AccountUser;
import ru.avelichko.NauJava.model.Role;

public interface AccountUserRepositoryCustom {

    // Создание пользователя
    Long createAccountUser(String name, String surname, String login, String password, Role role);

    // Удаление пользователя
    void deleteAccountUser(Long userId);

    /**
     * Находит всех пользователей с заданным именем
     * @param login логин пользователя
     */
    List<AccountUser> findByLogin(String login);

    /**
     * Находит всех пользователей с заданным название роли
     * @param roleTitle наименование роли
     */
    List<AccountUser> findByRole(String roleTitle);
}
