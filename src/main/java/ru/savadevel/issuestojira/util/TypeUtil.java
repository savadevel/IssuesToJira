package ru.savadevel.issuestojira.util;

import ru.savadevel.issuestojira.model.TypeJira;
import ru.savadevel.issuestojira.to.TypeTo;

public class TypeUtil {
    private TypeUtil() {
    }

    public static TypeJira getFromTo(TypeTo type) {
        return TypeJira.values()[type.ordinal()];
    }
}
