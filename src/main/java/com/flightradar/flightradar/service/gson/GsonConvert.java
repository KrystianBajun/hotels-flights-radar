package com.flightradar.flightradar.service.gson;

import com.flightradar.flightradar.model.trip.flight.FlightResponse;
import com.flightradar.flightradar.model.currency.CurrencyData;
import com.flightradar.flightradar.model.historicalFlight.HistoricalResponse;
import com.flightradar.flightradar.model.trip.hotel.HotelData;
import com.flightradar.flightradar.model.hotelGoogle.HotelGImageResponse;
import com.flightradar.flightradar.model.hotelGoogle.HotelGPlaceResponse;
import com.flightradar.flightradar.model.token.TokenData;

public interface GsonConvert {

    FlightResponse gsonFlight(String answer);

    HistoricalResponse gsonHistorical(String answer);

    TokenData gsonToken(String answer);

    HotelData gsonHotel(String answer);

    CurrencyData gsonCurrency(String answer);

    HotelGPlaceResponse gsonHotelGPlace(String answer);

    HotelGImageResponse gsonHotelGImage(String answer);

}