package ru.savadevel.issuestojira.util;

import ru.savadevel.issuestojira.model.ProjectJira;
import ru.savadevel.issuestojira.service.JiraService;
import ru.savadevel.issuestojira.to.FormTo;
import ru.savadevel.issuestojira.to.ProjectTo;
import ru.savadevel.issuestojira.util.exception.ApplicationException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

public class JiraUtil {
    private JiraUtil() {
    }

    public static void createIssue(FormTo formTo) {
        ProjectJira projectJira = getProjectJira(formTo);
        try (JiraService jiraService = new JiraService(formTo.getUrl(), formTo.getUsername(), formTo.getPassword())) {
            projectJira.getIssues()
                    .forEach(jiraService::createIssue);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
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
