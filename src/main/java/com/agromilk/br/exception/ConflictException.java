package com.agromilk.br.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ConflictException() {
        super();
    }

    public ConflictException(String message) {
        super(message, null, true, false);
    }

    public ConflictException(String message, Throwable throwable) {
        super(message, throwable, true, false);
    }
}
