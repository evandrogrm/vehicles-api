package com.example.vehicles.config.exception;

import com.example.vehicles.api.v1.service.exception.AbstractException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;


@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends AbstractException {
    private static final long serialVersionUID = 1L;

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatus() {
        return 403;
    }
}
