package ru.avelichko.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.avelichko.NauJava.model.Account;
import ru.avelichko.NauJava.repository.AccountRepository;
import ru.avelichko.NauJava.repository.AccountUserRepository;

@Component
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountUserRepository accountUserRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountUserRepository accountUserRepository) {
        this.accountRepository = accountRepository;
        this.accountUserRepository = accountUserRepository;
    }

    public Account getAccountByLogin(String login) {
        return accountUserRepository.findByLogin(login).getFirst().getAccount();
    }
}
