package ru.avelichko.NauJava.domain;

public enum ExpenseCategoryEnum {
    FOOD("FOOD"),
    TRANSPORT("TRANSPORT"),
    HEALTH("HEALTH"),
    HOUSING("HOUSING"),
    HOBBY("HOBBY"),
    CLOTHING("CLOTHING"),
    ENTERTAINMENTS("ENTERTAINMENTS"),
    OTHER("OTHER");

    private final String title;

    ExpenseCategoryEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
