package com.example.vehicles.api.v1.service.exception;

import java.util.Arrays;
import java.util.Date;

public class VehicleAlreadyRentException extends BadRequestException {

    public VehicleAlreadyRentException(String rentId, String vehicleId) {
        super("rent.vehicleAlreadyRent" + SEPARATOR + Arrays.asList(rentId, vehicleId), new Object[]{rentId, vehicleId});
    }
}
