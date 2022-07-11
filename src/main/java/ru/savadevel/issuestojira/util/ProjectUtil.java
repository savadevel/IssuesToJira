package ru.savadevel.issuestojira.util;

import ru.savadevel.issuestojira.model.ProjectJira;
import ru.savadevel.issuestojira.to.ProjectTo;

/**
 * Утилитный класс для работы с проектами
 */
public class ProjectUtil {

    private ProjectUtil() {
    }

    /**
     * Конвертирует проект в проект для Jira
     * @param projectTo проект для конвертации
     * @return проект для Jira
     */
    public static ProjectJira getFromTo(ProjectTo projectTo) {
        return new ProjectJira(projectTo.getKey(),
                IssueUtil.getFromTo(projectTo.getKey(), projectTo.getIssues(), projectTo.getDescriptions()));
    }
}
