package com.flightradar.flightradar.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Reservation has been already paid !")
public class ReservationAlreadyPaidException extends RuntimeException {

    private static final long serialVersionUID = 1L;



}
