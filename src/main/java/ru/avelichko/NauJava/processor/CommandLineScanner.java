package ru.avelichko.NauJava.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLineScanner implements CommandLineRunner {

    @Autowired
    private CommandProcessor commandProcessor;

    @Override
    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите команду. 'exit' для выхода.");
            while (true) {
                // Показать приглашение для ввода
                System.out.print("> ");
                String input = scanner.nextLine();
                // Выход из цикла, если введена команда "exit"
                if ("exit".equalsIgnoreCase(input.trim())) {
                    System.out.println("Выход из программы.");
                    break;
                }
                // Обработка команды
                commandProcessor.processCommand(input);
            }
        }
    }
}