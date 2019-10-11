package com.flightradar.flightradar.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(AllowedForUsers.condition)
public @interface AllowedForUsers {
    String condition = "hasAnyRole({'ROLE_ADMIN','ROLE_USER','ROLE_MODERATOR'})";
}
