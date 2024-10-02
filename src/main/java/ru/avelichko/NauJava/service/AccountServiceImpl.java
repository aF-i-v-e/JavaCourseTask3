package ru.avelichko.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.avelichko.NauJava.model.Account;
import ru.avelichko.NauJava.model.AccountCategory;
import ru.avelichko.NauJava.repository.AccountRepository;

import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(Long id, AccountCategory accountCategory, Double amount, Date date) {
        Account account = new Account(id, accountCategory, amount, date);
        accountRepository.create(account);
    }

    @Override
    public Double countAccountsByCategory(AccountCategory accountCategory) {
        List<Account> accounts = accountRepository.getAccountsByCategory(accountCategory);
        return accounts.stream().mapToDouble(Account::getAmount).sum();
    }

    @Override
    public Double countAccountsByDate(Class accountCategoryClass, Date dateStart, Date dateEnd) {
        List<Account> accounts = accountRepository
                .getAccountsByDate(dateStart, dateEnd).stream()
                .filter(account -> accountCategoryClass.isInstance(account.getAccountCategory())).toList();
        return accounts.stream().mapToDouble(Account::getAmount).sum();
    }
}
