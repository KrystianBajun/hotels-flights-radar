


package com.flightradar.flightradar.service.gson;

import com.flightradar.flightradar.model.trip.flight.FlightResponse;
import com.flightradar.flightradar.model.currency.CurrencyData;
import com.flightradar.flightradar.model.historicalFlight.HistoricalResponse;
import com.flightradar.flightradar.model.trip.hotel.HotelData;
import com.flightradar.flightradar.model.hotelGoogle.HotelGImageResponse;
import com.flightradar.flightradar.model.hotelGoogle.HotelGPlaceResponse;
import com.flightradar.flightradar.model.token.TokenData;
import com.flightradar.flightradar.util.OutputFromApiException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;


@Component
public class GsonConvertImp implements GsonConvert {

    @Override
    public FlightResponse gsonFlight(String answer) {

        Gson g = new GsonBuilder().create();

        FlightResponse flightResponse = null;
        try {
            flightResponse = g.fromJson(answer, FlightResponse.class);


        } catch (Exception e) {
            throw new OutputFromApiException("CheapFlight API output is empty, error :", e.toString());
        }

        return flightResponse;
    }

    @Override
    public HistoricalResponse gsonHistorical(String answer) {

        Gson g = new Gson();
        HistoricalResponse historicalResponseData = null;
        try {
            historicalResponseData = g.fromJson(answer, HistoricalResponse.class);

        } catch (Exception e) {
            throw new OutputFromApiException("HistoricalFlight API output is empty, error : ", e.toString());
        }
        return historicalResponseData;

    }

    @Override
    public TokenData gsonToken(String answer) {

        Gson g = new Gson();
        TokenData token;

        try {
            token = g.fromJson(answer, TokenData.class);

        } catch (Exception e) {
            throw new OutputFromApiException("HistoricalFlight API output is empty, error : ", e.toString());
        }
        return token;
    }

    @Override
    public HotelData gsonHotel(String answer) {
        // simulating, request from API because 30 min is on trial version only
       String test = "{    \"comparison\": [        {            \"vendor3-price\": \"US$135\",            \"vendor2\": \"Travelocity\",            \"Hotel\": \"SponsoredHotel Indigo Warsaw - Nowy Swiat\",            \"vendor1-price\": \"US$96\",            \"Best-price\": \"US$\\u00a0135US$\\u00a096HotelIndigo.com\",            \"vendor2-price\": \"US$96\",            \"vendor1\": \"Booking.com\",            \"vendor3\": \"Otel.com\"        },        {            \"vendor3-price\": \"US$119\",            \"vendor2\": \"Trip.com\",            \"Hotel\": \"Polonia Palace Hotel\",            \"vendor1-price\": \"US$80\",            \"Best-price\": \"US$\\u00a0119US$\\u00a080Booking.com\",            \"vendor2-price\": \"US$80\",            \"vendor1\": \"Travelocity\",            \"vendor3\": \"Otel.com\"        },        {            \"vendor3-price\": null,            \"vendor2\": null,            \"Hotel\": \"Ibis Budget Warszawa Centrum\",            \"vendor1-price\": \"US$29\",            \"Best-price\": \"US$\\u00a029ibis Budget\",            \"vendor2-price\": null,            \"vendor1\": \"TripAdvisor\",            \"vendor3\": null        },        {            \"vendor3-price\": \"US$94\",            \"vendor2\": \"Trip.com\",            \"Hotel\": \"Sofitel Warsaw Victoria Hotel\",            \"vendor1-price\": \"US$94\",            \"Best-price\": \"US$\\u00a094Booking.com\",            \"vendor2-price\": \"US$95\",            \"vendor1\": \"Sofitel\",            \"vendor3\": \"Travelocity\"        },        {            \"vendor3-price\": \"US$92\",            \"vendor2\": \"Trip.com\",            \"Hotel\": \"Mercure Warszawa Centrum\",            \"vendor1-price\": \"US$64\",            \"Best-price\": \"US$\\u00a092US$\\u00a064Booking.com\",            \"vendor2-price\": \"US$64\",            \"vendor1\": \"Travelocity\",            \"vendor3\": \"ZenHotels.com\"        },        {            \"vendor3-price\": \"US$60\",            \"vendor2\": \"Orbitz.com\",            \"Hotel\": \"Hampton by Hilton Warsaw City Centre\",            \"vendor1-price\": \"US$59\",            \"Best-price\": \"US$\\u00a059Hampton\",            \"vendor2-price\": \"US$59\",            \"vendor1\": \"Booking.com\",            \"vendor3\": \"Trip.com\"        },        {            \"vendor3-price\": \"US$41\",            \"vendor2\": \"Trip.com\",            \"Hotel\": \"SponsoredHoliday Inn Express Warsaw Airport\",            \"vendor1-price\": \"US$41\",            \"Best-price\": \"US$\\u00a041HIExpress.com\",            \"vendor2-price\": \"US$41\",            \"vendor1\": \"Booking.com\",            \"vendor3\": \"Orbitz.com\"        }    ]}";

        Gson g = new Gson();
        HotelData hotelData;

        try {
            hotelData = g.fromJson(test, HotelData.class);

        } catch (Exception e) {
            throw new OutputFromApiException("Hotel API output is empty, error : ", e.toString());
        }
        return hotelData;
    }

    @Override
    public CurrencyData gsonCurrency(String answer) {

        Gson g = new Gson();

        CurrencyData currencyData;

        try {
            currencyData = g.fromJson(answer, CurrencyData.class);

        } catch (Exception e) {
            throw new OutputFromApiException("Currency API output is empty, error : ", e.toString());
        }
        return currencyData;
    }

    @Override
    public HotelGPlaceResponse gsonHotelGPlace(String answer) {

        Gson g = new Gson();

        HotelGPlaceResponse hotelGPlaceResponse;

        try {
            hotelGPlaceResponse = g.fromJson(answer, HotelGPlaceResponse.class);

        } catch (Exception e) {
            throw new OutputFromApiException("Google Hotel (place) API output is empty, error : ", e.toString());
        }
        return hotelGPlaceResponse;
    }

    @Override
    public HotelGImageResponse gsonHotelGImage(String answer) {

        Gson g = new Gson();

        HotelGImageResponse hotelGImageResponse;

        try {
            hotelGImageResponse = g.fromJson(answer, HotelGImageResponse.class);

        } catch (Exception e) {

            throw new OutputFromApiException("Google Hotel (image) API output is empty, error : ", e.toString());

        }
        return hotelGImageResponse;
    }

}