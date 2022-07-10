package ru.savadevel.issuestojira.util.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class IllegalRequestDataException extends RuntimeException {


    private final transient BindingResult bindingResult;

    public IllegalRequestDataException(BindingResult bindingResult) {
        super(bindingResult.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; ")));
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}