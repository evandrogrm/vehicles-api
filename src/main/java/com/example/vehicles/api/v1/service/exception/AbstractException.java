package com.example.vehicles.api.v1.service.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractException extends Exception {
    private static final long serialVersionUID = 1L;
    private HttpStatus status;
    private final String message;
    private Object[] parameters;

    public AbstractException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.parameters = new Object[0];
        this.message = message;
    }

    public AbstractException(String message, Object[] parameters) {
        this(message);
        this.parameters = parameters;
    }

    public AbstractException(String message, Throwable cause) {
        super(message, cause);
        this.parameters = new Object[0];
        this.message = message;
    }

    public AbstractException(String message, Object[] parameters, Throwable cause) {
        this(message, cause);
        this.parameters = parameters;
    }

    public AbstractException(String message, Throwable cause, Object[] parameters) {
        this(message, cause);
        this.parameters = parameters;
    }

    public String getMessage() {
        return this.message;
    }

    public Object[] getParameters() {
        return this.parameters;
    }

    public abstract int getStatus();
}

