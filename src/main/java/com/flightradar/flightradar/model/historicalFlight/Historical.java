package com.flightradar.flightradar.model.historicalFlight;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Component
//@Table(name = "Historical", uniqueConstraints = @UniqueConstraint(columnNames = {"airline", "price", "origin", "destination", "tripClass", "airline", "departDate", "returnDate", "currency"}))

public class Historical {

    private Long id;
    private String airline;
    private BigDecimal price;
    private String origin;
    private String destination;
    private String tripClass;
    private String departDate;
    private String returnDate;
    private String userName;
    private String currency;


    public Historical() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getUserName() {
        return userName;
    }


    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTripClass() {
        return tripClass;
    }

    public void setTripClass(String tripClass) {
        this.tripClass = tripClass;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
