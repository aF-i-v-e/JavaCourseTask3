package ru.avelichko.NauJava.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.avelichko.NauJava.model.AccountUser;

public interface AccountUserRepository extends CrudRepository<AccountUser, Long> {
    /**
     * Находит всех пользователей с заданным именем
     * @param login логин пользователя
     */
    List<AccountUser> findByLogin(String login);

    /**
     * Находит всех пользователей с заданным название роли
     * @param roleTitle наименование роли
     */
    @Query("FROM AccountUser WHERE role.title = :roleTitle")
    List<AccountUser> findByRole(String roleTitle);
}
