package ru.savadevel.issuestojira.to;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Приоритет задачи
 */
@XmlType(name = "priority")
@XmlEnum
public enum PriorityTo {

    @XmlEnumValue("Highest")
    HIGHEST("Highest"),

    @XmlEnumValue("High")
    HIGH("High"),

    @XmlEnumValue("Medium")
    MEDIUM("Medium"),

    @XmlEnumValue("Low")
    LOW("Low"),

    @XmlEnumValue("Lowest")
    LOWEST("Lowest");

    private final String value;

    PriorityTo(String v) {
        value = v;
    }

    public String value() {
        return name();
    }

    public static PriorityTo fromValue(String v) {
        for (PriorityTo c: PriorityTo.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
