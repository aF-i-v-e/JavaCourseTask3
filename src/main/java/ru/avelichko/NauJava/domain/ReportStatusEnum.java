package ru.avelichko.NauJava.domain;

public enum ReportStatusEnum {
    CREATED("CREATED"),
    COMPLETED("COMPLETED"),
    ERROR("ERROR");

    private final String title;

    ReportStatusEnum(String title) {
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
