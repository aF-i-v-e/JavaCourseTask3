package ru.avelichko.NauJava.repository;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.avelichko.NauJava.domain.RoleEnum;
import ru.avelichko.NauJava.model.Account;
import ru.avelichko.NauJava.model.AccountUser;
import ru.avelichko.NauJava.model.Role;

import java.util.List;

@SpringBootTest
public class AccountUserRepositoryTest {
    private final RoleRepository roleRepository;
    private final AccountUserRepository accountUserRepository;

    @Autowired
    public AccountUserRepositoryTest(RoleRepository roleRepository, AccountUserRepository accountUserRepository) {
        this.roleRepository = roleRepository;
        this.accountUserRepository = accountUserRepository;
    }

    /**
     * Тестирование поиска пользователя по его логину
     */
    @Test
    void testFindAccountUserByLogin() {
        // given: генерация логина пользователя
        String login = RandomStringUtils.randomAlphabetic(10);

        // создание пользователя
        AccountUser accountUser = new AccountUser();
        accountUser.setAccount(new Account());
        accountUser.setLogin(login);
        accountUserRepository.save(accountUser);

        // when: поиск пользователя по логину
        AccountUser foundAccountUser = accountUserRepository.findByLogin(login).getFirst();

        // then:
        Assertions.assertNotNull(foundAccountUser);
        Assertions.assertEquals(accountUser.getAccountUserId(), foundAccountUser.getAccountUserId());
        Assertions.assertEquals(accountUser.getLogin(), foundAccountUser.getLogin());

        // clean objects:
        accountUserRepository.delete(accountUser);
    }

    @Test
    void testFindByRole() {
        // создание привилегированного пользователя администратора
        // с ролью Admin
        String admin = RoleEnum.TEST_ADMIN.toString();
        Role adminRole = new Role();
        adminRole.setTitle(admin);
        roleRepository.save(adminRole);

        AccountUser adminAccountUser = new AccountUser();
        adminAccountUser.setLogin(admin);
        adminAccountUser.setRole(adminRole);
        accountUserRepository.save(adminAccountUser);

        // создание обычного пользователя с ролью User
        String user = RoleEnum.TEST_USER.toString();
        Role userRole = new Role();
        userRole.setTitle(user);
        roleRepository.save(userRole);

        AccountUser accountUser = new AccountUser();
        accountUser.setLogin(user);
        accountUser.setRole(userRole);
        accountUserRepository.save(accountUser);

        // when: поиск пользователя по роли
        List<AccountUser> admins = accountUserRepository.findByRole(admin);

        // then:
        Assertions.assertNotNull(admins);
        Assertions.assertEquals(admins.size(), 1);
        Assertions.assertEquals(admins.getFirst().getAccountUserId(), adminAccountUser.getAccountUserId());
        Assertions.assertEquals(admins.getFirst().getLogin(), adminAccountUser.getLogin());

        // clean objects:
        accountUserRepository.delete(adminAccountUser);
        accountUserRepository.delete(accountUser);
        roleRepository.delete(adminRole);
        roleRepository.delete(userRole);
    }
}
