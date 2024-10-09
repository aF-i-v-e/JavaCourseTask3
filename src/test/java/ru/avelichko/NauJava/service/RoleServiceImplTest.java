package ru.avelichko.NauJava.service;

import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.avelichko.NauJava.model.AccountUser;
import ru.avelichko.NauJava.model.Role;
import ru.avelichko.NauJava.repository.AccountUserRepository;
import ru.avelichko.NauJava.repository.RoleRepository;

@SpringBootTest
public class RoleServiceImplTest {
    private final RoleService roleService;
    private final AccountUserRepository accountUserRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImplTest(RoleService roleService, AccountUserRepository accountUserRepository, RoleRepository roleRepository) {
        this.roleService = roleService;
        this.accountUserRepository = accountUserRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Тестирование удаления роли вместе с пользователями
     */
    @Test
    void testDeleteRoleInTx() {
        // given: создание роли
        Role adminRole = new Role();
        adminRole.setTitle(RandomStringUtils.randomAlphabetic(10));
        roleRepository.save(adminRole);

        // given: создание пользователя 1
        AccountUser accountUser1 = new AccountUser();
        accountUser1.setName(RandomStringUtils.randomAlphabetic(10));
        accountUser1.setRole(adminRole);
        accountUserRepository.save(accountUser1);

        // given: создание пользователя 2
        AccountUser accountUser2 = new AccountUser();
        accountUser2.setName(RandomStringUtils.randomAlphabetic(10));
        accountUser2.setRole(adminRole);
        accountUserRepository.save(accountUser2);

        // when: удаление роли
        roleService.deleteRole(adminRole.getTitle());

        // then: проверка отсутствия роли в БД
        Optional<Role> foundRole = roleRepository.findById(adminRole.getRoleId());
        Assertions.assertTrue(foundRole.isEmpty());

        // проверка отсутствия пользователя 1 в БД
        Optional<AccountUser> foundAccountUser1 = accountUserRepository.findById(accountUser1.getAccountUserId());
        Assertions.assertTrue(foundAccountUser1.isEmpty());

        // проверка отсутствия пользователя 2 в БД
        Optional<AccountUser> foundAccountUser2 = accountUserRepository.findById(accountUser2.getAccountUserId());
        Assertions.assertTrue(foundAccountUser2.isEmpty());
    }
}

