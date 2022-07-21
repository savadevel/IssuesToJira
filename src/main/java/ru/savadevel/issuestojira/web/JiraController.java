package ru.savadevel.issuestojira.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.savadevel.issuestojira.model.IssueJira;
import ru.savadevel.issuestojira.to.FormTo;
import ru.savadevel.issuestojira.util.JiraUtil;
import ru.savadevel.issuestojira.util.exception.IllegalRequestDataException;
import ru.savadevel.issuestojira.util.validation.FormToValidation;

import javax.validation.Valid;
import java.util.Map;

/**
 * Котроллер обрабатывающий запросы пользователя по загрузке / созданию задач в Jira
 */
@Controller
@Slf4j
public class JiraController {

    private static final String FORM_LOAD_ISSUES = "form";
    private static final String FORM_ATTRIBUTE = "formTo";
    private static final String REPORT_LOAD_ISSUES = "report";
    private static final String REPORT_ATTRIBUTE = "report";

    private final FormToValidation formToValidation;

    public JiraController(FormToValidation formToValidation) {
        this.formToValidation = formToValidation;
    }

    /**
     * Отображение формы загрузки / создания задач в Jira
     *
     * @param model атрибуты модели
     * @return страница формы
     */
    @GetMapping("/")
    public String form(Model model) {
        log.info("Request form load issues to Jira");
        model.addAttribute(FORM_ATTRIBUTE, new FormTo());
        return FORM_LOAD_ISSUES;
    }

    /**
     * Получает заданную пользователем форму загрузки / создания задач в Jira, выполняет их загрузку в Jira
     * При ошибках в форме загрузки / создания задач в Jira кидает исключение IllegalRequestDataException
     *
     * @param formTo        параметры загрузки / создания задач в Jira
     * @param bindingResult результаты проверки параметров формы загрузки / создания задач в Jira
     * @param model         атрибуты модели
     * @return страница отчета
     */
    @PostMapping("/")
    public String submit(@Valid @ModelAttribute(FORM_ATTRIBUTE) FormTo formTo, BindingResult bindingResult, Model model) {
        log.info("Submit form load issues to Jira: {} {}", formTo.getUrl(), formTo.getUsername());
        formToValidation.validate(formTo, bindingResult);
        if (bindingResult.hasErrors())
            throw new IllegalRequestDataException(bindingResult);
        Map<String, IssueJira> report = JiraUtil.createIssue(formTo);
        model.addAttribute(REPORT_ATTRIBUTE, report);
        return REPORT_LOAD_ISSUES;
    }
}
