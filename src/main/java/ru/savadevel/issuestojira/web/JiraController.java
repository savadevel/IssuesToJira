package ru.savadevel.issuestojira.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.savadevel.issuestojira.util.JiraUtil;

@Controller
public class JiraController {

    private static final String FORM_LOAD_ISSUES = "form";
    private static final String REPORT_LOAD_ISSUES = "report";

    @GetMapping("/")
    public String form(Model model) {
        model.addAttribute("jiraDto", new JiraDto());
        return FORM_LOAD_ISSUES;
    }

    @PostMapping("/")
    public String submit(@ModelAttribute("jiraDto") JiraDto jiraDto) {
        JiraUtil.createIssue(jiraDto);
        return REPORT_LOAD_ISSUES;
    }
}
