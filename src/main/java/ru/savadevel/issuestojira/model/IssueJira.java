package ru.savadevel.issuestojira.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 *  Задача в Jira
 */
@Data
@AllArgsConstructor
public class IssueJira {

    @NonNull
    private String projectKey;
    @NonNull
    private Integer id;
    private TypeJira type;
    private PriorityJira priority;
    private String summary;
}
