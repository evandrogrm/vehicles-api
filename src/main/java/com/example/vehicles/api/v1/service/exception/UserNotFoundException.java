package com.example.vehicles.api.v1.service.exception;

import java.util.Arrays;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String id) {
        super("user.notFound" + SEPARATOR + Arrays.asList(id), new Object[]{id});
    }
}
