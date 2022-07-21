package ru.savadevel.issuestojira.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.savadevel.issuestojira.util.exception.ApplicationException;
import ru.savadevel.issuestojira.util.exception.IllegalRequestDataException;

/**
 * Контроллер обрабатывает возникающие исключения в Приложении
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String ERROR_LOAD_ISSUES = "error";
    private static final String ERROR_ATTRIBUTE = "error";

    /**
     * Обрабатывает исключения на уровне Приложения
     *
     * @param exception исключение
     * @param model     м
     * @return страница с ошибкой
     */
    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String applicationError(ApplicationException exception, Model model) {
        model.addAttribute(ERROR_ATTRIBUTE, exception.getLocalizedMessage());
        log.error("applicationError: {}", exception.getMessage());
        return ERROR_LOAD_ISSUES;
    }

    /**
     * Обрабатывает при обработке входных параметров
     *
     * @param exception исключение
     * @param model     атрибуты модели
     * @return страница с ошибкой
     */
    @ExceptionHandler(IllegalRequestDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalRequestDataError(IllegalRequestDataException exception, Model model) {
        model.addAttribute(ERROR_ATTRIBUTE, exception.getBindingResult().getAllErrors());
        log.error("illegalRequestDataError: {}", exception.getMessage());
        return ERROR_LOAD_ISSUES;
    }

    /**
     * Обрабатывает исключения, которые на обработаны другими
     *
     * @param exception исключение
     * @param model     атрибуты модели
     * @return страница с ошибкой
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String globalExceptions(Exception exception, Model model) {
        model.addAttribute(ERROR_ATTRIBUTE, exception.getLocalizedMessage());
        log.error("globalExceptions: {}", exception.getMessage());
        return ERROR_LOAD_ISSUES;
    }
}
