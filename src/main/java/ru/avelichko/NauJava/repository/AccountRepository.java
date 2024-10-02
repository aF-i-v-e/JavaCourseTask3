package ru.avelichko.NauJava.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.avelichko.NauJava.model.Account;
import ru.avelichko.NauJava.model.AccountCategory;

import java.util.Date;
import java.util.List;

@Component
public class AccountRepository implements CrudRepository<Account, Long> {
    private final List<Account> accountContainer;

    @Autowired
    public AccountRepository(List<Account> accountContainer) {
        this.accountContainer = accountContainer;
    }

    @Override
    public void create(Account entity) {
        accountContainer.add(entity);
    }

    @Override
    public Account read(Long aLong) {
        return accountContainer
                .stream().filter(account -> account.getId().equals(aLong))
                .findFirst().orElseGet(Account::new);
    }

    @Override
    public void update(Account entity) {
        delete(entity.getId());
        create(entity);
    }

    @Override
    public void delete(Long aLong) {
        accountContainer.removeIf(account -> account.getId().equals(aLong));
    }

    @Override
    public List<Account> getAccountsByCategory(AccountCategory accountCategory) {
        return accountContainer.stream()
                .filter(account -> account.getAccountCategory().equals(accountCategory))
                .toList();
    }

    @Override
    public List<Account> getAccountsByDate(Date dateStart, Date dateEnd) {
        return accountContainer.stream()
                .filter(account -> account.getDate().after(dateStart)
                        && account.getDate().before(dateEnd))
                .toList();
    }
}
