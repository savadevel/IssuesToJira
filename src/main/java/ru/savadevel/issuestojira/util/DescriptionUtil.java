package ru.savadevel.issuestojira.util;

import lombok.experimental.UtilityClass;
import ru.savadevel.issuestojira.to.DescriptionTo;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Утилитный класс для работы с описанием задачи
 */
@UtilityClass
public class DescriptionUtil {

    protected static Map<Integer, DescriptionTo> getDescriptionToGroupById(List<DescriptionTo> descriptionsTo) {
        return descriptionsTo
                .stream()
                .collect(Collectors.toMap(DescriptionTo::getId, Function.identity()));
    }
}
