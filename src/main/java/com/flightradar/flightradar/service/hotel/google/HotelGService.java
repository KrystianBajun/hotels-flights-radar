package com.flightradar.flightradar.service.hotel.google;

import com.flightradar.flightradar.model.hotelGoogle.HotelGImageResponse;
import com.flightradar.flightradar.model.hotelGoogle.HotelGPlaceResponse;
import com.flightradar.flightradar.model.hotelGoogle.Images;
import com.flightradar.flightradar.model.hotelGoogle.Places;
import com.flightradar.flightradar.model.trip.hotel.Comparison;
import com.flightradar.flightradar.service.connect.ConnectServiceGoogle;
import com.flightradar.flightradar.service.gson.GsonConvert;
import com.flightradar.flightradar.service.url.GoogleUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
public class HotelGService {
    @Autowired
    private GoogleUrl googleUrl;
    @Autowired
    private
    ConnectServiceGoogle connectServiceGoogle;
    @Autowired
    private GsonConvert gsonConvert;


    public Map<Integer, List<Places>> getHotelGPlaces(ArrayList<Comparison> hotelOutput) {

        Map<Integer, List<Places>> hotelGPlaceList = new HashMap<>();
        HotelGPlaceResponse hotelPlaceResponse;

        for (int i = 0; i < hotelOutput.size(); i++) {

            String requestPlace = googleUrl.placeRequestUrl(hotelOutput.get(i).getHotel());
            String outputPlace = connectServiceGoogle.connectPlace(requestPlace);
            hotelPlaceResponse = gsonConvert.gsonHotelGPlace(outputPlace);
            hotelGPlaceList.put(i, hotelPlaceResponse.getPlaces_results());
        }
        return hotelGPlaceList;
    }

    public Map<Integer, List<Images>> getHotelGImages(ArrayList<Comparison> hotelOutput) {

        Map<Integer, List<Images>> hotelGImageList = new HashMap<>();
        HotelGImageResponse hotelImageResponse;

        for (int i = 0; i < hotelOutput.size(); i++) {

            String requestImage = googleUrl.imageRequestUrl(hotelOutput.get(i).getHotel());
            String outputImage = connectServiceGoogle.connectImage(requestImage);
            hotelImageResponse = gsonConvert.gsonHotelGImage(outputImage);
            hotelGImageList.put(i, hotelImageResponse.getImage_results());
        }
        return hotelGImageList;
    }
}
