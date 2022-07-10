package ru.savadevel.issuestojira.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.savadevel.issuestojira.to.FormTo;
import ru.savadevel.issuestojira.util.JiraUtil;
import ru.savadevel.issuestojira.util.exception.IllegalRequestDataException;
import ru.savadevel.issuestojira.util.validation.FormToValidation;

import javax.validation.Valid;

@Controller
public class JiraController {

    private static final Logger log = LoggerFactory.getLogger(JiraController.class);

    private static final String FORM_LOAD_ISSUES = "form";
    private static final String REPORT_LOAD_ISSUES = "report";

    private final FormToValidation formToValidation;

    public JiraController(FormToValidation formToValidation) {
        this.formToValidation = formToValidation;
    }

    @GetMapping("/")
    public String form(Model model) {
        log.info("Request form load issues to Jira");
        model.addAttribute("formTo", new FormTo());
        return FORM_LOAD_ISSUES;
    }

    @PostMapping("/")
    public String submit(@Valid @ModelAttribute("formTo") FormTo formTo, BindingResult bindingResult) {
        log.info("Submit form load issues to Jira: {} {}", formTo.getUrl(), formTo.getUsername());
        formToValidation.validate(formTo, bindingResult);
        if (bindingResult.hasErrors())
            throw new IllegalRequestDataException(bindingResult);
        JiraUtil.createIssue(formTo);
        return REPORT_LOAD_ISSUES;
    }
}
