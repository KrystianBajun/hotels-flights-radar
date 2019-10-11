package com.flightradar.flightradar.model.trip.hotel;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class HotelRequest {

    private String name;
    private String site;
    private BigDecimal price;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}







