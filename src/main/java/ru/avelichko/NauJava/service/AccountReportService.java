package ru.avelichko.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.avelichko.NauJava.model.Account;
import ru.avelichko.NauJava.model.AccountReport;
import ru.avelichko.NauJava.model.Expense;
import ru.avelichko.NauJava.repository.AccountReportRepository;
import ru.avelichko.NauJava.repository.AccountRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class AccountReportService {
    private final AccountRepository accountRepository;

    private final String allCategoryReport = "Отчёт по всем категориям";
    private final String specificCategoryReport = "Отчёт по категории";
    private final AccountReportRepository accountReportRepository;

    @Autowired
    public AccountReportService(AccountRepository accountRepository,
                                AccountReportRepository accountReportRepository) {
        this.accountRepository = accountRepository;
        this.accountReportRepository = accountReportRepository;
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
        accountReportRepository.save(accountReport);
        return true;
    }

    private List<Expense> getExpensesInTimeRange(Account account, LocalDateTime start, LocalDateTime end) {
        return account.getExpenses().stream()
                .filter(expense -> expense.getDate().isAfter(start))
                .filter(expense -> expense.getDate().isBefore(end))
                .toList();
    }
}
