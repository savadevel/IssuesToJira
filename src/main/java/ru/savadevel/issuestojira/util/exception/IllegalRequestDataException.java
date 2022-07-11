package ru.savadevel.issuestojira.util.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

/**
 * Исключение при обработке входных параметров
 */
public class IllegalRequestDataException extends RuntimeException {

    private final transient BindingResult bindingResult;

    public IllegalRequestDataException(BindingResult bindingResult) {
        super(bindingResult.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; ")));
        this.bindingResult = bindingResult;
    }

    /**
     * Возвращает результаты проверки входных параметров
     * @return результаты проверки входных параметров
     */
    public BindingResult getBindingResult() {
        return bindingResult;
    }
}