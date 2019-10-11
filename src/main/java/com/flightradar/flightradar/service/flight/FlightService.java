package com.flightradar.flightradar.service.flight;

import com.flightradar.flightradar.model.trip.TripRequest;
import com.flightradar.flightradar.service.parser.CsvParser;
import com.flightradar.flightradar.model.trip.flight.Flight;
import com.flightradar.flightradar.model.trip.flight.FlightDeserialization;
import com.flightradar.flightradar.model.trip.hotel.Comparison;
import com.flightradar.flightradar.model.trip.hotel.Hotel;
import com.flightradar.flightradar.model.hotelGoogle.Images;
import com.flightradar.flightradar.model.hotelGoogle.Places;
import com.flightradar.flightradar.repository.CurrencyRepository;
import com.flightradar.flightradar.repository.FlightRepository;
import com.flightradar.flightradar.service.hotel.HotelCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.flightradar.flightradar.service.Static.CurrentUser;

@Component
public class FlightService {

    @Autowired
    private
    FlightRepository flightRepository;

    @Autowired
    private
    HotelCurrencyService hotelCurrencyService;

    @Autowired
    private CsvParser csvParser;

    @Autowired
    private
    CurrencyRepository currencyRepository;


    public void tripSave(Map<String, Map<String, FlightDeserialization>> flightOutputMap, TripRequest tripRequest,
                         Map<Integer, List<Places>> hotelGPlaceList,
                         Map<Integer, List<Images>> hotelGImageList,
                         ArrayList<Comparison> hotelOutputList,
                         Map<String, BigDecimal> priceDetails
    ) {
        boolean isHotelGListEqualToHotelOutputList = hotelGPlaceList.keySet().size() == hotelOutputList.size() & hotelGPlaceList.keySet().size() == hotelGImageList.size();


        Flight flight;
        for (String keyOut : flightOutputMap.keySet()) {

            for (String keyIn : flightOutputMap.get(keyOut).keySet()) {
                flight = new Flight();
                flight.setCurrency(tripRequest.getCurrency());
                flight.setAirline(csvParser.airlineParser(flightOutputMap.get(keyOut).get(keyIn).getAirline()));
                flight.setAirlineIata(flightOutputMap.get(keyOut).get(keyIn).getAirline());
                flight.setDepartureTime(flightOutputMap.get(keyOut).get(keyIn).getDepartureTime());
                flight.setReturnTime(flightOutputMap.get(keyOut).get(keyIn).getReturnTime());
                flight.setFlightNumber(flightOutputMap.get(keyOut).get(keyIn).getFlightNumber());
                flight.setPrice(flightOutputMap.get(keyOut).get(keyIn).getPrice());
                flight.setExpiresAt(flightOutputMap.get(keyOut).get(keyIn).getExpiresAt());
                flight.setDestination(tripRequest.getDestination());
                flight.setDeparture(tripRequest.getDeparture());

                flight.setHighestPrice(priceDetails.get("higestPrice"));
                flight.setMinimalPrice(priceDetails.get("minimalPrice"));
                flight.setMedian(priceDetails.get("median"));
                flight.setAverage(priceDetails.get("average"));

                flight.setUserName(CurrentUser().getUsername());


                if (isHotelGListEqualToHotelOutputList) {
                    Hotel hotel;

                    System.out.println();
                    BigDecimal exchangeRate = currencyRepository.findByName(tripRequest.getCurrency()).getExchangeRate();
                    for (int i = 0; i < hotelOutputList.size(); i++) {
                        Comparison array = hotelOutputList.get(i);
                        hotel = new Hotel();

                        hotel.setImage(hotelGImageList.get(i).get(1).getImage());
                        hotel.setImage2(hotelGImageList.get(i).get(2).getImage());
                        hotel.setImage3(hotelGImageList.get(i).get(7).getImage());
                        hotel.setImage4(hotelGImageList.get(i).get(4).getImage());

                        hotel.setHotelLink(hotelGImageList.get(i).get(0).getLink());

                        hotel.setAddress(hotelGPlaceList.get(i).get(0).getAddress());
                        hotel.setWebRating(hotelGPlaceList.get(i).get(0).getRating());
                        hotel.setPhone(hotelGPlaceList.get(i).get(0).getPhone());
                        hotel.setCategory(hotelGPlaceList.get(i).get(0).getCategory());

                        hotel.setDescribe(hotelGPlaceList.get(i).get(0).getSnippet());
                     /*   hotel.setLatitude(hotelGPlaceList.get(i).get(0).getGps_coordinates().getLatitude());
                        hotel.setLongitude(hotelGPlaceList.get(i).get(0).getGps_coordinates().getLongitude());*/

                        hotel.setCurrency(tripRequest.getCurrency());
                        hotel.setName(array.getHotel());
                        hotel.setSite(array.getVendor1());
                        hotel.setSite(array.getVendor2());
                        hotel.setSite(array.getVendor3());
                        hotel.setPrice(hotelCurrencyService.amountCalculator(array.getVendor1Price(), exchangeRate));
                        hotel.setPrice(hotelCurrencyService.amountCalculator(array.getVendor2Price(), exchangeRate));
                        hotel.setPrice(hotelCurrencyService.amountCalculator(array.getVendor3Price(), exchangeRate));

                        flight.setHotel(hotel);
                        flightRepository.save(flight);

                    }
                }
            }
        }
    }
}
