package com.example.vehicles.api.v1.service.exception;

import java.util.Arrays;
import java.util.Date;

public class RentStartDateBeforeNowException extends BadRequestException {

    public RentStartDateBeforeNowException(Date date) {
        super("rent.beforeNow" + SEPARATOR + Arrays.asList(date.toString()), new Object[]{date});
    }
}
