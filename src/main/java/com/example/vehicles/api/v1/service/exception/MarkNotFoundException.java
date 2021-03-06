package com.example.vehicles.api.v1.service.exception;

import java.util.Arrays;

public class MarkNotFoundException extends NotFoundException {

    public MarkNotFoundException(String id) {
        super("mark.notFound" + SEPARATOR + Arrays.asList(id), new Object[]{id});
    }
}
