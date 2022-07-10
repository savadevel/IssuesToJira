package ru.savadevel.issuestojira.util;

import ru.savadevel.issuestojira.model.ProjectJira;
import ru.savadevel.issuestojira.service.JiraService;
import ru.savadevel.issuestojira.to.ProjectTo;
import ru.savadevel.issuestojira.web.JiraDto;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

public class JiraUtil {
    private JiraUtil() {
    }

    public static void createIssue(JiraDto jiraDto) {
        ProjectJira projectJira = getProjectJira(jiraDto);
        try (JiraService jiraService = new JiraService(jiraDto.getUrl(), jiraDto.getUsername(), jiraDto.getPassword())) {
            projectJira.getIssues()
                    .forEach(jiraService::createIssue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static ProjectJira getProjectJira(JiraDto jiraDto) {
        try {
            JAXBContext context = JAXBContext.newInstance(ProjectTo.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ProjectTo projectTo = (ProjectTo) unmarshaller.unmarshal(jiraDto.getFile().getInputStream());
            return ProjectUtil.getFromTo(projectTo);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
