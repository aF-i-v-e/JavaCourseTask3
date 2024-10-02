package ru.avelichko.NauJava.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.avelichko.NauJava.handler.AccountHandler;

@Component
public class CommandProcessor {
    private final AccountHandler accountHandler;

    @Autowired
    public CommandProcessor(AccountHandler accountHandler) {
        this.accountHandler = accountHandler;
    }

    public void processCommand(String input) {
        String[] cmd = input.split(" ");
        switch (cmd[0]) {
            case "createExpense" -> accountHandler.createExpense(cmd);
            case "createIncome" -> accountHandler.createIncome(cmd);
            case "countExpensesByCategory" -> accountHandler.countExpensesByCategory(cmd[1]);
            case "countIncomesByCategory" -> accountHandler.countIncomesByCategory(cmd[1]);
            case "countExpensesByDate" -> accountHandler.countExpensesByDate(cmd);
            case "countIncomesByDate" -> accountHandler.countIncomesByDate(cmd);
            default -> System.out.println("Введена неизвестная команда.");
        }
    }
}
