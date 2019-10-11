
package com.flightradar.flightradar.model.trip.flight;

import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Date;

@Component
public class FlightDeserialization {

    private BigDecimal price;
    private String airline;

    @SerializedName("flight_number")
    private int flightNumber;

    @SerializedName("departure_at")
    private Date departureTime;

    @SerializedName("return_at")
    private Date returnTime;

    @SerializedName("expires_at")
    private String expiresAt;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return "FlightDeserialization{" +
                "price=" + price +
                ", airline='" + airline + '\'' +
                ", flightNumber=" + flightNumber +
                ", departureAt='" + departureTime + '\'' +
                ", returnAt='" + returnTime + '\'' +
                ", expiresAt='" + expiresAt + '\'' +
                '}';
    }
}