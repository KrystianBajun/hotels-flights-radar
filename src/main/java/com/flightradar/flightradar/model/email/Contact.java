package com.flightradar.flightradar.model.email;

import org.springframework.stereotype.Component;

@Component
public class Contact extends Email {

    private String phone;

    private String emailAddress;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
