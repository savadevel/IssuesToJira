package ru.savadevel.issuestojira.to;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Параметры формы загрузки задач в Jira, включает параметры подключения к Jira и задачи, которые требуется создать
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormTo {

    @NotBlank(message = "URL of Jira is mandatory")
    private String url;
    @NotBlank(message = "Username is mandatory")
    private String username;
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotNull
    private MultipartFile file;
}
