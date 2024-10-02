package ru.avelichko.NauJava.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.avelichko.NauJava.model.ExpenseCategory;
import ru.avelichko.NauJava.model.IncomeCategory;
import ru.avelichko.NauJava.service.AccountService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class AccountHandler {
    private final AccountService accountService;

    @Autowired
    public AccountHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    public void createExpense(String[] input) {
        Long id = getRandomLong();
        try {
            ExpenseCategory expenseCategory = ExpenseCategory.getByUpperCaseName(input[1]);
            Double amount = Double.valueOf(input[2]);
            Date date = parseDate(input[3]);
            accountService.createAccount(id, expenseCategory, amount, date);
            System.out.println("Расход успешно записан!");
        } catch (ParseException e) {
            System.out.println("Возникла ошибка при парсинге аргументов команды.");
        }
    }

    public void createIncome(String[] input) {
        Long id = getRandomLong();
        try {
            IncomeCategory incomeCategory = IncomeCategory.getByUpperCaseName(input[1]);
            Double amount = Double.valueOf(input[2]);
            Date date = parseDate(input[3]);
            accountService.createAccount(id, incomeCategory, amount, date);
            System.out.println("Доход успешно записан!");
        } catch (Exception e) {
            System.out.println("Возникла ошибка при парсинге аргументов команды.");
        }
    }

    public void countExpensesByCategory(String category) {
        try {
            ExpenseCategory expenseCategory = ExpenseCategory.getByUpperCaseName(category);
            double amount = accountService.countAccountsByCategory(expenseCategory);
            System.out.printf("Суммарный расход по категории %1$s составляет %2$.2f\n", category, amount);
        } catch (Exception e) {
            System.out.println("Возникла ошибка при парсинге аргументов команды.");
        }
    }

    public void countIncomesByCategory(String category) {
        try {
            IncomeCategory incomeCategory = IncomeCategory.getByUpperCaseName(category);
            double amount = accountService.countAccountsByCategory(incomeCategory);
            System.out.printf("Суммарный доход по категории %1$s составляет %2$.2f\n", category, amount);
        } catch (Exception e) {
            System.out.println("Возникла ошибка при парсинге аргументов команды.");
        }
    }

    public void countExpensesByDate(String[] input) {
        try {
            Date dateStart = parseDate(input[1]);
            Date dateEnd = parseDate(input[2]);
            double amount = accountService.countAccountsByDate(ExpenseCategory.class, dateStart, dateEnd);
            System.out.printf("Суммарный расход с %1$s до %2$s составляет %3$.2f\n", input[1], input[2], amount);
        } catch (Exception e) {
            System.out.println("Возникла ошибка при парсинге аргументов команды.");
        }
    }

    public void countIncomesByDate(String[] input) {
        try {
            Date dateStart = parseDate(input[1]);
            Date dateEnd = parseDate(input[2]);
            double amount = accountService.countAccountsByDate(IncomeCategory.class, dateStart, dateEnd);
            System.out.printf("Суммарный доход с %1$s до %2$s составляет %3$.2f\n", input[1], input[2], amount);
        } catch (Exception e) {
            System.out.println("Возникла ошибка при парсинге аргументов команды.");
        }
    }

    private static Long getRandomLong() {
        Random rand = new Random();
        long min = -1_000_000_000_000_000L;
        long max = -min;
        return min + rand.nextLong(max - min + 1);
    }

    private static Date parseDate(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.parse(dateString);
    }
}
