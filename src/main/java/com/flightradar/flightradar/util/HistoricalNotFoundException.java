package com.flightradar.flightradar.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason= "No historical flight found!")
public class HistoricalNotFoundException extends NullPointerException {

   private static final long serialVersionUID = 1L;

}
