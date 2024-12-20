package ru.avelichko.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.avelichko.NauJava.model.*;
import ru.avelichko.NauJava.repository.AccountReportRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class AccountReportService {
    private final AccountReportRepository accountReportRepository;

    private final String allCategoryReport = "Отчёт по всем категориям";
    private final String specificCategoryReport = "Отчёт по категории ";

    @Autowired
    public AccountReportService(AccountReportRepository accountReportRepository) {
        this.accountReportRepository = accountReportRepository;
    }

    public AccountReport findByAccountReportId(long id) {
        return accountReportRepository.findById(id).orElse(null);
    }

    public Boolean fillAllExpenseReport(Account account, AccountReport accountReport) {
        accountReport.setAccount(account);
        accountReport.setCategoryInfo(allCategoryReport);
        List<Expense> expenses = getExpensesInTimeRange(account,
                accountReport.getDateStart(),
                accountReport.getDateEnd()
        );
        Optional<Expense> maxExpense = expenses.stream().max(Comparator.comparingDouble(Expense::getAmount));
        if (maxExpense.isPresent()) {
            String dopInfo = String.format("Максимальная величина расходов составляет %.2f в категории %s",
                    maxExpense.get().getAmount(),
                    maxExpense.get().getExpenseCategory().getExpenseCategoryName());
            accountReport.setDopInfo(dopInfo);
        } else {
            return false;
        }
        Double totalSum = expenses.stream().mapToDouble(Expense::getAmount).sum();
        accountReport.setTotalSum(totalSum);
        saveIfNotExists(accountReport);
        return true;
    }

    public Boolean fillSpecificExpenseReport(Account account,
                                             AccountReport accountReport,
                                             ExpenseCategory expenseCategory) {
        accountReport.setAccount(account);
        accountReport.setCategoryInfo(specificCategoryReport + expenseCategory.getExpenseCategoryName());
        List<Expense> expenses = getExpensesInTimeRange(account,
                accountReport.getDateStart(),
                accountReport.getDateEnd())
                .stream()
                .filter(expense -> expense.getExpenseCategory().getExpenseCategoryId().equals(expenseCategory.getExpenseCategoryId()))
                .toList();
        Double totalSum = expenses.stream().mapToDouble(Expense::getAmount).sum();
        accountReport.setTotalSum(totalSum);
        accountReport.setDopInfo("Количество записей в категории составляет " + expenses.size());
        saveIfNotExists(accountReport);
        return true;
    }

    private List<Expense> getExpensesInTimeRange(Account account, LocalDateTime start, LocalDateTime end) {
        return account.getExpenses().stream()
                .filter(expense -> expense.getDate().isAfter(start))
                .filter(expense -> expense.getDate().isBefore(end))
                .toList();
    }

    private List<Income> getIncomesInTimeRange(Account account, LocalDateTime start, LocalDateTime end) {
        return account.getIncomes().stream()
                .filter(expense -> expense.getDate().isAfter(start))
                .filter(expense -> expense.getDate().isBefore(end))
                .toList();
    }

    public Boolean fillAllIncomeReport(Account account, AccountReport accountReport) {
        accountReport.setAccount(account);
        accountReport.setCategoryInfo(allCategoryReport);
        List<Income> incomes = getIncomesInTimeRange(account,
                accountReport.getDateStart(),
                accountReport.getDateEnd()
        );
        Optional<Income> maxIncome = incomes.stream().max(Comparator.comparingDouble(Income::getAmount));
        if (maxIncome.isPresent()) {
            String dopInfo = String.format("Максимальная величина доходов составляет %.2f в категории %s",
                    maxIncome.get().getAmount(),
                    maxIncome.get().getIncomeCategory().getIncomeCategoryName());
            accountReport.setDopInfo(dopInfo);
        } else {
            return false;
        }
        Double totalSum = incomes.stream().mapToDouble(Income::getAmount).sum();
        accountReport.setTotalSum(totalSum);
        saveIfNotExists(accountReport);
        return true;
    }

    public Boolean fillSpecificIncomeReport(Account account,
                                            AccountReport accountReport,
                                            IncomeCategory incomeCategory) {
        accountReport.setAccount(account);
        accountReport.setCategoryInfo(specificCategoryReport + incomeCategory.getIncomeCategoryName());
        List<Income> incomes = getIncomesInTimeRange(account,
                accountReport.getDateStart(),
                accountReport.getDateEnd())
                .stream()
                .filter(income -> income.getIncomeCategory().getIncomeCategoryId()
                        .equals(incomeCategory.getIncomeCategoryId()))
                .toList();
        Double totalSum = incomes.stream().mapToDouble(Income::getAmount).sum();
        accountReport.setTotalSum(totalSum);
        accountReport.setDopInfo("Количество записей в категории составляет " + incomes.size());
        saveIfNotExists(accountReport);
        return true;
    }

    private void saveIfNotExists(AccountReport accountReport) {
        List<AccountReport> existAccountReport = accountReportRepository
                .findByAccount(accountReport.getAccount()).stream()
                .filter(report -> report.getDateStart().equals(accountReport.getDateStart()))
                .filter(report -> report.getDateEnd().equals(accountReport.getDateEnd()))
                .filter(report -> Double.compare(report.getTotalSum(), accountReport.getTotalSum()) == 0)
                .filter(report -> Objects.equals(report.getCategoryInfo(), accountReport.getCategoryInfo()))
                .filter(report -> Objects.equals(report.getDopInfo(), accountReport.getDopInfo()))
                .toList();
        if (existAccountReport.isEmpty())
            accountReportRepository.save(accountReport);
        else
            accountReport.setAccountReportId(existAccountReport.getFirst().getAccountReportId());
    }
}
