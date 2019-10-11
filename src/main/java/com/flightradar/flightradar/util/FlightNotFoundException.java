package com.flightradar.flightradar.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No flight found")
public class FlightNotFoundException extends NullPointerException {

    private static final long serialVersionUID = 1L;



    public FlightNotFoundException(String error, String exception) {

        System.out.println(error + exception);

    }
    public FlightNotFoundException(String error) {

        System.out.println(error);

    }

    public FlightNotFoundException() {


    }




}
