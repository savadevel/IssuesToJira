package ru.savadevel.issuestojira.web;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.savadevel.issuestojira.util.exception.ApplicationException;
import ru.savadevel.issuestojira.util.exception.IllegalRequestDataException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_LOAD_ISSUES = "error";
    private static final String ERROR_ATTRIBUTE = "error";

    @ExceptionHandler(ApplicationException.class)
    public String applicationError(ApplicationException exception, Model model) {
        model.addAttribute(ERROR_ATTRIBUTE, exception.getMessage());
        return ERROR_LOAD_ISSUES;
    }

    @ExceptionHandler(IllegalRequestDataException.class)
    public String applicationError(IllegalRequestDataException exception, Model model) {
        model.addAttribute(ERROR_ATTRIBUTE, exception.getBindingResult().getAllErrors());
        return ERROR_LOAD_ISSUES;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationExceptions(Exception exception, Model model) {
        model.addAttribute(ERROR_ATTRIBUTE, exception.getMessage());
        return ERROR_LOAD_ISSUES;
    }
}
