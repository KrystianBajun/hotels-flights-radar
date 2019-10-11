package com.flightradar.flightradar.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = " Problem with API request ")
public class OutputFromApiException extends NullPointerException {

    public OutputFromApiException(String error, String exception) {


        System.out.println(error + exception);
    }
    public OutputFromApiException(String error) {


        System.out.println(error );
    }

}
