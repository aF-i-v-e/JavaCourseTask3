package ru.avelichko.NauJava.service;

import ru.avelichko.NauJava.model.AccountCategory;

import java.util.Date;

public interface AccountService {
    void createAccount(Long id, AccountCategory accountCategory, Double amount, Date date);

    Double countAccountsByCategory(AccountCategory accountCategory);

    Double countAccountsByDate(Class accountCategory, Date dateStart, Date dateEnd);
}
