package ru.avelichko.NauJava.model;

/**
 * Категория расходов
 */
public enum ExpenseCategory implements AccountCategory {
    FOOD("food"),
    TRANSPORT("transport"),
    HEALTH("health"),
    HOUSING("housing"),
    HOBBY("hobby"),
    CLOTHING("clothing"),
    ENTERTAINMENTS("entertainments"),
    OTHER("other");

    private final String category;

    ExpenseCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public static ExpenseCategory getByUpperCaseName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        return ExpenseCategory.valueOf(name.toUpperCase());
    }
}
