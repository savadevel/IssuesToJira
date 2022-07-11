package ru.savadevel.issuestojira.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.savadevel.issuestojira.util.exception.ApplicationException;
import ru.savadevel.issuestojira.util.exception.IllegalRequestDataException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String ERROR_LOAD_ISSUES = "error";
    private static final String ERROR_ATTRIBUTE = "error";

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String applicationError(ApplicationException exception, Model model) {
        model.addAttribute(ERROR_ATTRIBUTE, exception.getLocalizedMessage());
        log.error("applicationError: {}", exception.getMessage());
        return ERROR_LOAD_ISSUES;
    }

    @ExceptionHandler(IllegalRequestDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalRequestDataError(IllegalRequestDataException exception, Model model) {
        model.addAttribute(ERROR_ATTRIBUTE, exception.getBindingResult().getAllErrors());
        log.error("illegalRequestDataError: {}", exception.getMessage());
        return ERROR_LOAD_ISSUES;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String globalExceptions(Exception exception, Model model) {
        model.addAttribute(ERROR_ATTRIBUTE, exception.getLocalizedMessage());
        log.error("globalExceptions: {}", exception.getMessage());
        return ERROR_LOAD_ISSUES;
    }
}
