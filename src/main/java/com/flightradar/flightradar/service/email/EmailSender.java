package com.flightradar.flightradar.service.email;

import java.text.ParseException;

public interface EmailSender {
    void sendEmail(String to,String subject, String message);
    void sendNewsletter(String title, String description);
    void sendFeedBackEmail(String to, String subject, String message);
    void isTripFinishedAndPaid() throws ParseException;
}
