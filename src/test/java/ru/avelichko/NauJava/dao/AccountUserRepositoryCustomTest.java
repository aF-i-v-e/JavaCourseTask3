package ru.avelichko.NauJava.dao;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.avelichko.NauJava.model.AccountUser;
import ru.avelichko.NauJava.model.Role;
import ru.avelichko.NauJava.modelEnum.RoleEnum;
import ru.avelichko.NauJava.repository.RoleRepository;

import java.util.List;

@SpringBootTest
public class AccountUserRepositoryCustomTest {

    private final AccountUserRepositoryCustom accountUserRepositoryCustom;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public AccountUserRepositoryCustomTest(AccountUserRepositoryCustom accountUserRepositoryCustom, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.accountUserRepositoryCustom = accountUserRepositoryCustom;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    /**
     * Используется один раз для создания тестового пользователя
     */
    @Disabled
    @Test
    void testCreateUser() {
        String name = "Иван";
        String surname = "Иванов";
        String login = "iivanov";
        String password = passwordEncoder.encode("12345");

        // генерация роли и её сохранение
        String user = RoleEnum.USER.getTitle();
        Role userRole = new Role();
        userRole.setTitle(user);
        roleRepository.save(userRole);

        long userId = accountUserRepositoryCustom.createAccountUser(name, surname, login, password, userRole);
        Assertions.assertNotEquals(userId, -1L);
    }

    @Disabled
    @Test
    void testCreateUserAdmin() {
        String name = "admin";
        String surname = "admin";
        String login = "admin";
        String password = passwordEncoder.encode("admin");

        // генерация роли и её сохранение
        String user = RoleEnum.ADMIN.getTitle();
        Role userRole = new Role();
        userRole.setTitle(user);
        roleRepository.save(userRole);

        long userId = accountUserRepositoryCustom.createAccountUser(name, surname, login, password, userRole);
        Assertions.assertNotEquals(userId, -1L);
    }

    /**
     * Тестирование поиска пользователя по его логину
     */
    @Test
    void testFindAccountUserByLogin() {
        // given: генерация пользователя
        String name = RandomStringUtils.randomAlphabetic(10);
        String surname = RandomStringUtils.randomAlphabetic(10);
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);

        // генерация роли и её сохранение
        Role userRole = new Role();
        String user = RoleEnum.TEST_USER.toString();
        ;
        userRole.setTitle(user);
        roleRepository.save(userRole);
        // создание пользователя
        long userId = accountUserRepositoryCustom.createAccountUser(name, surname, login, password, userRole);

        // when: поиск пользователя по логину
        AccountUser foundAccountUser = accountUserRepositoryCustom.findByLogin(login).getFirst();

        // then:
        Assertions.assertNotNull(foundAccountUser);
        Assertions.assertEquals(userId, foundAccountUser.getAccountUserId());
        Assertions.assertEquals(name, foundAccountUser.getName());
        Assertions.assertEquals(surname, foundAccountUser.getSurname());
        Assertions.assertEquals(login, foundAccountUser.getLogin());
        Assertions.assertEquals(password, foundAccountUser.getPassword());

        // clean objects:
        accountUserRepositoryCustom.deleteAccountUser(userId);
        roleRepository.delete(userRole);
    }

    @Test
    void testFindByRole() {
        // создание привилегированного пользователя администратора
        // с ролью Admin
        String admin = RoleEnum.TEST_ADMIN.toString();
        ;
        Role adminRole = new Role();
        adminRole.setTitle(admin);
        roleRepository.save(adminRole);

        long adminId = accountUserRepositoryCustom.createAccountUser(admin, admin, admin, admin, adminRole);

        // создание обычного пользователя с ролью User
        String user = RoleEnum.TEST_USER.toString();
        Role userRole = new Role();
        userRole.setTitle(user);
        roleRepository.save(userRole);

        long userId = accountUserRepositoryCustom.createAccountUser(user, user, user, user, userRole);

        // when: поиск пользователя по роли
        List<AccountUser> admins = accountUserRepositoryCustom.findByRole(admin);

        // then:
        Assertions.assertNotNull(admins);
        Assertions.assertEquals(admins.size(), 1);
        Assertions.assertEquals(admins.getFirst().getAccountUserId(), adminId);
        Assertions.assertEquals(admins.getFirst().getName(), admin);
        Assertions.assertEquals(admins.getFirst().getSurname(), admin);
        Assertions.assertEquals(admins.getFirst().getLogin(), admin);
        Assertions.assertEquals(admins.getFirst().getPassword(), admin);

        // clean objects:
        accountUserRepositoryCustom.deleteAccountUser(adminId);
        accountUserRepositoryCustom.deleteAccountUser(userId);
        roleRepository.delete(adminRole);
        roleRepository.delete(userRole);
    }
}
