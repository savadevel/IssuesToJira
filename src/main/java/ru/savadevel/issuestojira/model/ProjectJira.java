package ru.savadevel.issuestojira.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

/**
 * Проект в Jira
 */
@Data
@AllArgsConstructor
public class ProjectJira {

    @NonNull
    private String key;
    private List<IssueJira> issues;
}
