package com.agromilk.br.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = -6390159374123499911L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message, null, true, false);
    }
}
