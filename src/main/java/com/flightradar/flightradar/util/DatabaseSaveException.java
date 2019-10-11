package com.flightradar.flightradar.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Problem with saving request!")
public class DatabaseSaveException extends NullPointerException {

    private static final long serialVersionUID = 1L;


}
