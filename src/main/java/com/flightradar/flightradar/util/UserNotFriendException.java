package com.flightradar.flightradar.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="First you have to invite user to friends!")
public class UserNotFriendException extends RuntimeException {

    private static final long serialVersionUID = 1L;



}
