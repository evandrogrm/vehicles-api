package com.example.vehicles.api.v1.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends AbstractException {
    private static final long serialVersionUID = 1L;
    public static final int STATUS = 400;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Object[] parameters) {
        super(message, parameters);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(String message, Object[] parameters, Throwable cause) {
        super(message, parameters, cause);
    }

    public int getStatus() {
        return STATUS;
    }
}
