package ru.avelichko.NauJava.model;

/**
 * Категория доходов
 */
public enum IncomeCategory implements AccountCategory {
    WAGES("wages"),
    SCHOLARSHIP("scholarship"),
    OTHER("other");

    private final String category;

    IncomeCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public static IncomeCategory getByUpperCaseName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        return IncomeCategory.valueOf(name.toUpperCase());
    }
}
