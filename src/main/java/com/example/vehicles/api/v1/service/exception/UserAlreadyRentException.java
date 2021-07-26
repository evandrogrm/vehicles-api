package com.example.vehicles.api.v1.service.exception;

import java.util.Arrays;

public class UserAlreadyRentException extends BadRequestException {

    public UserAlreadyRentException(String rentId, String userId) {
        super("rent.userAlreadyRent" + SEPARATOR + Arrays.asList(rentId, userId), new Object[]{rentId, userId});
    }
}
