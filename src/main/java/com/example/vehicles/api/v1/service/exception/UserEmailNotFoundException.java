package com.example.vehicles.api.v1.service.exception;

import java.util.Arrays;

public class UserEmailNotFoundException extends NotFoundException {

    public UserEmailNotFoundException(String email) {
        super("user.email" + SEPARATOR + Arrays.asList(email), new Object[]{email});
    }
}
