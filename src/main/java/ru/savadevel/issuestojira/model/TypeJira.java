package ru.savadevel.issuestojira.model;

public enum TypeJira {
    TASK(10001L),
    EPIC(10002L),
    SUBTASK(10003L);

    private final Long id;

    TypeJira(Long id) {
        this.id = id;
    }

    public Long id() {
        return id;
    }

    public String value() {
        return name();
    }

    public static TypeJira fromValue(Long id) {
        for (TypeJira c: TypeJira.values()) {
            if (c.id.equals(id)) {
                return c;
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }
}
