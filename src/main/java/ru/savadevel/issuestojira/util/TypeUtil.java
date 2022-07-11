package ru.savadevel.issuestojira.util;

import ru.savadevel.issuestojira.model.TypeJira;
import ru.savadevel.issuestojira.to.TypeTo;

/**
 * Утилитный класс для работы с типами задач
 */
public class TypeUtil {
    private TypeUtil() {
    }

    /**
     * Конвертирует тип задачи в тип задачи для Jira
     * @param type тип задачи для конвертации
     * @return тип задачи для Jira
     */
    public static TypeJira getFromTo(TypeTo type) {
        return TypeJira.values()[type.ordinal()];
    }
}
