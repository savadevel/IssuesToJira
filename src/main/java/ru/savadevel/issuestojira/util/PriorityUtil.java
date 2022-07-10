package ru.savadevel.issuestojira.util;

import ru.savadevel.issuestojira.model.PriorityJira;
import ru.savadevel.issuestojira.to.PriorityTo;

public class PriorityUtil {
    private PriorityUtil() {
    }

    public static PriorityJira getFromTo(PriorityTo priorityTo) {
        return PriorityJira.values()[priorityTo.ordinal()];
    }
}
