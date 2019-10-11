package com.flightradar.flightradar.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = " Wrong response code ")
public class ConnectRuntimeException extends RuntimeException {

    public ConnectRuntimeException(String error, Integer responseCode) {
        System.out.println(error + responseCode);
    }
}