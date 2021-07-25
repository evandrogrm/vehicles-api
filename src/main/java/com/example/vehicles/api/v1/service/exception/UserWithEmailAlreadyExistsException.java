package com.example.vehicles.api.v1.service.exception;

import java.util.Arrays;

public class UserWithEmailAlreadyExistsException extends BadRequestException {

    public UserWithEmailAlreadyExistsException(String email) {
        super("user.create.email.exists.{0};" + Arrays.asList(email), new Object[]{email});
    }
}
