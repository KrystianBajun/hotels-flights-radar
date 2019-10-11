package com.flightradar.flightradar.service.url;

import org.springframework.stereotype.Component;

import static com.flightradar.flightradar.service.url.HiddenKey.Google_KEY_;


//Serpow API - Google search results


@Component
public class GoogleUrl {


    public String placeRequestUrl(String hotelName) {

        String parsedHotelName = hotelName.replaceAll(" ", "+");
        String connectUrlString =
                "https://api.serpwow.com/live/search?api_key="
                        + Google_KEY_ + "&q=" + parsedHotelName +
                        "&google_domain=google.com&search_type=places";


        return connectUrlString;

    }


    public String imageRequestUrl(String hotelName) {

        String parsedHotelName = hotelName.replaceAll(" ", "+");

        String connectUrlString =
                "https://api.serpwow.com/live/search?api_key="
                        + Google_KEY_ + "&q=" + parsedHotelName +
                        "&search_type=images";


        return connectUrlString;

    }


}