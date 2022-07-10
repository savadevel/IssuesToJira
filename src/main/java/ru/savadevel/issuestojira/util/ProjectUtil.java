package ru.savadevel.issuestojira.util;

import ru.savadevel.issuestojira.model.ProjectJira;
import ru.savadevel.issuestojira.to.ProjectTo;

public class ProjectUtil {

    private ProjectUtil() {
    }

    public static ProjectJira getFromTo(ProjectTo projectTo) {
        return new ProjectJira(projectTo.getKey(),
                IssueUtil.getFromTo(projectTo.getKey(), projectTo.getIssues(), projectTo.getDescriptions()));
    }
}
