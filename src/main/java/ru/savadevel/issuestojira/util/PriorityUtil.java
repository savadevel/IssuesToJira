package ru.savadevel.issuestojira.util;

import ru.savadevel.issuestojira.model.PriorityJira;
import ru.savadevel.issuestojira.to.PriorityTo;

/**
 * Утилитный класс для работы с приоритетами задач
 */
public class PriorityUtil {
    private PriorityUtil() {
    }

    /**
     * Конвертирует приоритет задачи в приоритет задачи для Jira
     * @param priorityTo приоритет для конвертации
     * @return приоритет задачи для Jira
     */
    public static PriorityJira getFromTo(PriorityTo priorityTo) {
        return PriorityJira.values()[priorityTo.ordinal()];
    }
}
