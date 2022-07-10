package ru.savadevel.issuestojira.model;

public enum PriorityJira {
    HIGHEST(1L),
    HIGH(2L),
    MEDIUM(3L),
    LOW(4L),
    LOWEST(5L);

    private final Long id;

    PriorityJira(Long id) {
        this.id = id;
    }

    public Long id() {
        return id;
    }

    public String value() {
        return name();
    }

    public static PriorityJira fromValue(Long id) {
        for (PriorityJira c: PriorityJira.values()) {
            if (c.id.equals(id)) {
                return c;
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }
}
