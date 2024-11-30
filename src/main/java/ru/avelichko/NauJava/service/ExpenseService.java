package ru.avelichko.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.avelichko.NauJava.model.Account;
import ru.avelichko.NauJava.model.Expense;
import ru.avelichko.NauJava.repository.ExpenseRepository;

import java.util.ArrayList;

@Component
public class ExpenseService {
    
    private ExpenseRepository expenseRepository;

    @Autowired
    ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ArrayList<Expense> getExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();
        expenseRepository.findAll().forEach(expenses::add);
        return expenses;
    }

    public void createExpense(Expense expense, Account account) {
        expense.setAccount(account);
        expenseRepository.save(expense);
    }

    public void deleteExpenseById(Long id) {
        expenseRepository.deleteById(id);
    }
}