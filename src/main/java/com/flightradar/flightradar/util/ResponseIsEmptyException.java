package com.flightradar.flightradar.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Upps no results found! Please change your search details.")
public class ResponseIsEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;



}
