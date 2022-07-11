package ru.savadevel.issuestojira;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.savadevel.issuestojira.web.GlobalExceptionHandler;
import ru.savadevel.issuestojira.web.JiraController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class IssuesToJiraApplicationTests {

    @Autowired
    private JiraController jiraController;

    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void contextLoads() {
        assertThat(jiraController).isNotNull();
        assertThat(globalExceptionHandler).isNotNull();
    }

}
