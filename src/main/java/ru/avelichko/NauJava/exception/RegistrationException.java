package ru.avelichko.NauJava.exception;

import java.lang.Exception;

public class RegistrationException extends Exception {
    public RegistrationException(String message) {
        super(message);
    }
}
