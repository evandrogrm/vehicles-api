package com.example.vehicles.api.v1.service.exception;

import java.util.Arrays;

public class RentNotFoundException extends NotFoundException {

    public RentNotFoundException(String id) {
        super("rent.notFound" + SEPARATOR + Arrays.asList(id), new Object[]{id});
    }
}
