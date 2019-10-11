
package com.flightradar.flightradar.model.trip.hotel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class HotelData {


    private Comparison[] comparison;

    public Comparison[] getComparison() {
        return comparison;
    }

    public void setComparison(Comparison[] comparison) {
        this.comparison = comparison;
    }

    @Override
    public String toString() {
        return "ClassPojo [comparison = " + comparison + "]";
    }
}


