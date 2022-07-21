package ru.savadevel.issuestojira.util;

import lombok.experimental.UtilityClass;
import ru.savadevel.issuestojira.model.IssueJira;
import ru.savadevel.issuestojira.model.ProjectJira;
import ru.savadevel.issuestojira.service.JiraService;
import ru.savadevel.issuestojira.to.FormTo;
import ru.savadevel.issuestojira.to.ProjectTo;
import ru.savadevel.issuestojira.util.exception.ApplicationException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Утилитный класс для работы с сервисом Jira
 */
@UtilityClass
public class JiraUtil {

    /**
     * Создание задач в Jira, при ошибках кидает исключение ApplicationException
     *
     * @param formTo форма с параметрами подключения к Jira и задачами Jira, которые требуется создать
     * @return отчет по созданию задач в Jira, содержит пары: ключ задачи в Jira и переданная задача Jira
     */
    public static Map<String, IssueJira> createIssue(FormTo formTo) {
        ProjectJira projectJira = getProjectJira(formTo);
        Map<String, IssueJira> result = new HashMap<>();
        try (JiraService jiraService = new JiraService(formTo.getUrl(), formTo.getUsername(), formTo.getPassword())) {
            for (IssueJira issue : projectJira.getIssues()) {
                result.put(jiraService.createIssue(issue), issue);
            }
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
        return result;
    }

    private static ProjectJira getProjectJira(FormTo formTo) {
        try {
            JAXBContext context = JAXBContext.newInstance(ProjectTo.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ProjectTo projectTo = (ProjectTo) unmarshaller.unmarshal(formTo.getFile().getInputStream());
            return ProjectUtil.getFromTo(projectTo);
        } catch (JAXBException | IOException e) {
            throw new ApplicationException(e.getMessage());
        }
    }
}
