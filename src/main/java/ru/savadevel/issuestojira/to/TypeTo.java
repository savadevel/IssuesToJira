package ru.savadevel.issuestojira.to;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Тип задачи
 */
@XmlType(name = "type")
@XmlEnum
public enum TypeTo {

    @XmlEnumValue("task")
    TASK("task"),

    @XmlEnumValue("Epic")
    EPIC("Epic"),

    @XmlEnumValue("Subtask")
    SUBTASK("Subtask");

    private final String value;

    TypeTo(String v) {
        value = v;
    }

    public String value() {
        return name();
    }

    public static TypeTo fromValue(String v) {
        for (TypeTo c: TypeTo.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
