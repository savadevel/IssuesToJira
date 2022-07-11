package ru.savadevel.issuestojira.util.exception;

/**
 * Исключение на уровне Приложения
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException(String msg) {
        super(msg);
    }
}
