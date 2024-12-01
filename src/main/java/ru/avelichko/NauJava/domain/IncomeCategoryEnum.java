package ru.avelichko.NauJava.domain;

public enum IncomeCategoryEnum {
    SALARY("SALARY"),
    SCHOLARSHIP("SCHOLARSHIP"),
    ADVANCE_PAYMENT("ADVANCE_PAYMENT"),
    OTHER("OTHER");

    private final String title;

    IncomeCategoryEnum(String title) {
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
