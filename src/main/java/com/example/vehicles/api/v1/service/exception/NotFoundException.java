package com.example.vehicles.api.v1.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends AbstractException {
    private static final long serialVersionUID = 1L;
    public static final int STATUS = 404;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Object[] parameters) {
        super(message, parameters);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message, Object[] parameters, Throwable cause) {
        super(message, parameters, cause);
    }

    public int getStatus() {
        return STATUS;
    }
}
