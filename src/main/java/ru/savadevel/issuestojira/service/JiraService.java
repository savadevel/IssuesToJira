package ru.savadevel.issuestojira.service;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import ru.savadevel.issuestojira.model.IssueJira;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;

public class JiraService implements Closeable {
    private final String username;
    private final String password;
    private final String jiraUrl;

    private final JiraRestClient restClient;

    public JiraService(String jiraUrl, String username, String password) {
        this.jiraUrl = jiraUrl;
        this.username = username;
        this.password = password;
        this.restClient = getJiraRestClient();
    }

    private JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
    }

    private URI getJiraUri() {
        return URI.create(this.jiraUrl);
    }

    public String createIssue(IssueJira issue) {
        IssueRestClient issueClient = restClient.getIssueClient();
        IssueInput newIssue = new IssueInputBuilder()
                .setProjectKey(issue.getProjectKey())
                .setSummary(issue.getSummary())
                .setPriorityId(issue.getPriority().id())
                .setIssueTypeId(issue.getType().id())
                .build();
        return issueClient.createIssue(newIssue).claim().getKey();
    }

    @Override
    public void close() throws IOException {
        if (restClient == null) {
            return;
        }
        restClient.close();
    }
}
