package ru.avelichko.NauJava.repository;

import ru.avelichko.NauJava.model.Account;
import ru.avelichko.NauJava.model.AccountCategory;

import java.util.Date;
import java.util.List;

public interface CrudRepository<T, ID> {
    void create(T entity);

    T read(ID id);

    void update(T entity);

    void delete(ID id);

    List<Account> getAccountsByCategory(AccountCategory accountCategory);

    List<Account> getAccountsByDate(Date dateStart, Date dateEnd);
}
