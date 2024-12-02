package ru.avelichko.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.avelichko.NauJava.model.Account;
import ru.avelichko.NauJava.model.Income;
import ru.avelichko.NauJava.repository.IncomeRepository;

@Component
public class IncomeService {
    private final IncomeRepository incomeRepository;

    @Autowired
    IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public void createIncome(Income income, Account account) {
        income.setAccount(account);
        incomeRepository.save(income);
    }

    public void deleteIncomeById(Long id) {
        incomeRepository.deleteById(id);
    }
}
