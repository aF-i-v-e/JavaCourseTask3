package ru.avelichko.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.avelichko.NauJava.exception.RegistrationException;
import ru.avelichko.NauJava.model.Account;
import ru.avelichko.NauJava.model.AccountUser;
import ru.avelichko.NauJava.model.Role;
import ru.avelichko.NauJava.modelEnum.RoleEnum;
import ru.avelichko.NauJava.repository.AccountRepository;
import ru.avelichko.NauJava.repository.AccountUserRepository;
import ru.avelichko.NauJava.repository.RoleRepository;

import java.util.Collections;
import java.util.List;

@Component
public class AccountUserService implements UserDetailsService {

    private final AccountUserRepository accountUserRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountUserService(AccountUserRepository accountUserRepository, RoleRepository roleRepository, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountUserRepository = accountUserRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AccountUser accountUser = accountUserRepository.findByLogin(login).getFirst();
        return new org.springframework.security.core.userdetails.User(
                accountUser.getLogin(),
                accountUser.getPassword(),
                mapRolesToAuthorities(accountUser.getRole()));
    }

    private List<? extends GrantedAuthority> mapRolesToAuthorities(Role role) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getTitle()));
    }

    public Long addAccountUser(AccountUser accountUser) throws RegistrationException {
        // Проверка оригинальности логина пользователя
        List<AccountUser> logins = accountUserRepository.findByLogin(accountUser.getLogin());
        if (!logins.isEmpty()) {
            throw new RegistrationException("Пользователь с переданным логином уже существует");
        }

        // Получение роли пользователя т.к. админы могут создаваться только вручную
        List<Role> userRole = roleRepository.findByTitle(RoleEnum.USER.getTitle());
        if (userRole == null || userRole.isEmpty()) {
            roleRepository.save(new Role(RoleEnum.USER.toString()));
            System.out.println("Создана роль пользователя в системе.");
        }
        accountUser.setRole(userRole.getFirst());

        // Создание связанного аккаунта
        Account account = new Account();
        account.setExpenses(Collections.emptyList());
        account.setIncomes(Collections.emptyList());
        accountRepository.save(account);
        accountUser.setAccount(account);

        // Обязательное шифрование пароля перед записью в бд
        accountUser.setPassword(passwordEncoder.encode(accountUser.getPassword()));
        AccountUser savedUser = accountUserRepository.save(accountUser);
        return savedUser.getAccountUserId();
    }
}
