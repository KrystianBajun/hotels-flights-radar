package com.flightradar.flightradar.service;

import com.flightradar.flightradar.model.user.CustomUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Static {


    public static CustomUserDetail CurrentUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail myUser = (CustomUserDetail) auth.getPrincipal();


        return myUser;
    }


    public static String GenerateUUID() {

        return UUID.randomUUID().toString();
    }

    public static String ActualDateString() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();


        return dateFormat.format(date);
    }

    public static Date ActualDate() throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String formattedDate = dateFormat.format(date);

        Date formatDate = null;
        try {

            formatDate = new SimpleDateFormat("yyyy-MM-dd").parse(formattedDate);

            System.out.println(formatDate);

        } catch (ParseException e) {

            System.out.println("ParseException occured: " + e.getMessage());

        }
        return formatDate;
    }


    public static String RandomGenerator() {


        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();


        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();


    }


}
