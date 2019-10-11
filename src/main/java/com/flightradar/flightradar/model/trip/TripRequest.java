package com.flightradar.flightradar.model.trip;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class TripRequest {

    @NotNull
    private String dateOut;
    @NotNull
    private String dateBack;
    @NotNull
    private String departure;
    private String destination;
    private String currency;

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public String getDateBack() {
        return dateBack;
    }

    public void setDateBack(String dateBack) {
        this.dateBack = dateBack;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDestination() {


        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }


}




