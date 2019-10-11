package com.flightradar.flightradar.model.hotelGoogle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelGImageResponse
{
    private List<Images>  image_results;

    public List<Images> getImage_results() {
        return image_results;
    }

    public void setImage_results(List<Images> image_results) {
        this.image_results = image_results;
    }

    @Override
    public String toString() {
        return "HotelGImageResponse{" +
                "image_results=" + image_results +
                '}';
    }
}
			
	