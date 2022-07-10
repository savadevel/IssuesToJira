package ru.savadevel.issuestojira.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.savadevel.issuestojira.model.IssueJira;
import ru.savadevel.issuestojira.to.FormTo;
import ru.savadevel.issuestojira.util.JiraUtil;
import ru.savadevel.issuestojira.util.exception.IllegalRequestDataException;
import ru.savadevel.issuestojira.util.validation.FormToValidation;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class JiraController {

    private static final Logger log = LoggerFactory.getLogger(JiraController.class);

    private static final String FORM_LOAD_ISSUES = "form";
    private static final String FORM_ATTRIBUTE = "formTo";
    private static final String REPORT_LOAD_ISSUES = "report";
    private static final String REPORT_ATTRIBUTE = "report";

    private final FormToValidation formToValidation;

    public JiraController(FormToValidation formToValidation) {
        this.formToValidation = formToValidation;
    }

    @GetMapping("/")
    public String form(Model model) {
        log.info("Request form load issues to Jira");
        model.addAttribute(FORM_ATTRIBUTE, new FormTo());
        return FORM_LOAD_ISSUES;
    }

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
