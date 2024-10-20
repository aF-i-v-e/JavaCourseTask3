package ru.avelichko.NauJava.modelEnum;

public enum RoleEnum {
    ADMIN("admin"), USER("user");

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
