package ru.avelichko.NauJava.modelEnum;

public enum RoleEnum {
    ADMIN("ADMIN"),
    USER("USER"),
    TEST_ADMIN("TEST_ADMIN"),
    TEST_USER("TEST_USER");

    private String title;

    RoleEnum(String title) {
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
