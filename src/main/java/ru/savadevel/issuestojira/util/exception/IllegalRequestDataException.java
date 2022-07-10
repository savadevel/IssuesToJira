package ru.savadevel.issuestojira.util.exception;

import org.springframework.validation.BindingResult;

public class IllegalRequestDataException extends RuntimeException {


    private final transient BindingResult bindingResult;

    public IllegalRequestDataException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}