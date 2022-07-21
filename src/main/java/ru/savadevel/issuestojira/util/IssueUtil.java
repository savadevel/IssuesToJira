package ru.savadevel.issuestojira.util;

import lombok.experimental.UtilityClass;
import ru.savadevel.issuestojira.model.IssueJira;
import ru.savadevel.issuestojira.to.DescriptionTo;
import ru.savadevel.issuestojira.to.IssueTo;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Утилитный класс для работы с задачами
 */

@UtilityClass
public class IssueUtil {

    /**
     * Конвертирует список задач в задачи для Jira
     *
     * @param projectKey     ключ проекта
     * @param issuesTo       задачи для конвертации
     * @param descriptionsTo описание задач для конвертации
     * @return список задач для Jira
     */
    public static List<IssueJira> getFromTo(String projectKey, List<IssueTo> issuesTo, List<DescriptionTo> descriptionsTo) {
        Map<Integer, IssueTo> issuesToGroupById = getIssueToGroupById(issuesTo);
        Map<Integer, DescriptionTo> descriptionsToGroupById = DescriptionUtil.getDescriptionToGroupById(descriptionsTo);
        return issuesToGroupById.keySet()
                .stream()
                .map(id -> getFromTo(projectKey, issuesToGroupById.get(id), descriptionsToGroupById.get(id)))
                .collect(Collectors.toList());
    }

    protected static Map<Integer, IssueTo> getIssueToGroupById(List<IssueTo> issuesTo) {
        return issuesTo
                .stream()
                .collect(Collectors.toMap(IssueTo::getId, Function.identity()));
    }

    protected static IssueJira getFromTo(String projectKey, IssueTo issueTo, DescriptionTo descriptionTo) {
        return new IssueJira(
                projectKey,
                issueTo.getId(),
                TypeUtil.getFromTo(issueTo.getType()),
                PriorityUtil.getFromTo(issueTo.getPriority()),
                descriptionTo.getSummary());
    }
}
