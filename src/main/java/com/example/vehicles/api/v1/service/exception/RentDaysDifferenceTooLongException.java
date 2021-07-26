package com.example.vehicles.api.v1.service.exception;

import java.util.Arrays;
import java.util.Date;

public class RentDaysDifferenceTooLongException extends BadRequestException {

    public RentDaysDifferenceTooLongException(int difference) {
        super("rent.daysDifferenceGreaterThanMaximum" + SEPARATOR + Arrays.asList(difference), new Object[]{difference});
    }
}
