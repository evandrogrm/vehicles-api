package com.example.vehicles.api.v1.service.exception;

import java.util.Arrays;

public class MarkWithNameAlreadyExistsException extends BadRequestException {

    public MarkWithNameAlreadyExistsException(String name) {
        super("mark.create.name.exists.{0};" + Arrays.asList(name), new Object[]{name});
    }
}
