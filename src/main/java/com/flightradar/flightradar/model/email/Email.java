package com.flightradar.flightradar.model.email;

public class Email {

    private String title;
    private String header;
    private String message;
    private String sender;

    public Email() {
    }

    public Email(String title, String header, String message, String sender) {
        this.title = title;
        this.header = header;
        this.message = message;
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
