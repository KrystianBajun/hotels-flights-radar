package com.flightradar.flightradar.model.hotelGoogle;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class HotelGPlaceResponse
{
    private List<Places>  places_results;

    public List<Places> getPlaces_results() {
        return places_results;
    }

    public void setPlaces_results(List<Places> places_results) {
        this.places_results = places_results;
    }

    @Override
    public String toString() {
        return "HotelGPlaceResponse{" +
                "places_results=" + places_results +
                '}';
    }
}
