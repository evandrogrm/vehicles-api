package com.example.vehicles.api.v1.service.exception;

import java.util.Arrays;

public class VehicleNotFoundException extends NotFoundException {

    public VehicleNotFoundException(String id) {
        super("vehicle.notFound.{0};" + Arrays.asList(id), new Object[]{id});
    }
}
