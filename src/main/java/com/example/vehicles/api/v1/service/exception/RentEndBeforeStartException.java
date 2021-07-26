package com.example.vehicles.api.v1.service.exception;

import java.util.Arrays;
import java.util.Date;

public class RentEndBeforeStartException extends BadRequestException {

    public RentEndBeforeStartException(Date date) {
        super("rent.endBeforeStart" + SEPARATOR + Arrays.asList(date.toString()), new Object[]{date});
    }
}
