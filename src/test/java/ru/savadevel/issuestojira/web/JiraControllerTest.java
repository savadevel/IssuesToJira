package ru.savadevel.issuestojira.web;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import ru.savadevel.issuestojira.to.FormTo;
import ru.savadevel.issuestojira.util.JiraUtil;

import java.util.HashMap;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JiraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnFormLoadIssuesToJira() throws Exception {
        mockMvc
                .perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Форма загрузки задач в Jira")));
    }

    @Test
    void shouldReturnReportLoadedIssuesToJira() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file",
                "InputFile.xml",
                "text/plain",
                ("<data>" +
                        "<projectKey>PR</projectKey>" +
                        "<issues><issue><id>1</id><type>task</type><priority>Highest</priority></issue></issues>" +
                        "<descriptions><description><id>1</id><summary>Task case 1</summary></description></descriptions>" +
                        "</data>").getBytes());

        try (MockedStatic<JiraUtil> util = Mockito.mockStatic(JiraUtil.class)) {
            util.when(() -> JiraUtil.createIssue(any(FormTo.class)))
                    .thenReturn(new HashMap<>());
            mockMvc
                    .perform(multipart("/")
                            .file(multipartFile)
                            .param("url", "https://123.atlassian.net")
                            .param("username", "123@mail.com")
                            .param("password", "123"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Отчет по загрузке задач в Jira")));
        }
    }
}