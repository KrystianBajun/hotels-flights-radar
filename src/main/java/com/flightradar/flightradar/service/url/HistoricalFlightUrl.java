package com.flightradar.flightradar.service.url;

import com.flightradar.flightradar.model.trip.TripRequest;
import com.flightradar.flightradar.service.parser.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.flightradar.flightradar.service.url.HiddenKey.Flight_KEY_;

@Component
public class HistoricalFlightUrl {

    @Autowired
    private CsvParser csvParser;

    public String url(TripRequest tripRequest) {

        int limit = 6;

        String connectUrlString =
                "http://api.travelpayouts.com/v2/prices/latest?origin="
                        + csvParser.airportParser(tripRequest.getDeparture()) + "&destination=" + csvParser.airportParser(tripRequest.getDestination()) +
                        "&depart_date=" + tripRequest.getDateOut() +
                        "&currency=" + tripRequest.getCurrency() +
                        "&return_date=" + tripRequest.getDateBack() +
                        "&limit=" + limit +
                        "&token=";


        return connectUrlString + Flight_KEY_;
    }

    public String lastYearUrl(TripRequest tripRequest) {
        StringBuilder lastYearDep = new StringBuilder(tripRequest.getDateOut());
        StringBuilder lastYearRet = new StringBuilder(tripRequest.getDateOut());

        lastYearDep.setCharAt(4, '8');
        lastYearRet.setCharAt(4, '8');

        String connectUrlString =
                "http://api.travelpayouts.com/v2/prices/latest?origin="
                        + csvParser.airportParser(tripRequest.getDeparture()) + "&destination=" + csvParser.airportParser(tripRequest.getDestination()) +
                        "&depart_date=" + lastYearDep +
                        "&currency=" + tripRequest.getCurrency() +
                        "&return_date=" + lastYearDep +
                        "&limit=" + 34 +
                        "&token=";

        return connectUrlString + Flight_KEY_;
    }

    public String twoYearsUrl(TripRequest tripRequest) {
        StringBuilder lastYearDep = new StringBuilder(tripRequest.getDateOut());
        StringBuilder lastYearRet = new StringBuilder(tripRequest.getDateOut());

        lastYearDep.setCharAt(4, '7');
        lastYearRet.setCharAt(4, '7');

        String connectUrlString =
                "http://api.travelpayouts.com/v2/prices/latest?origin="
                        + csvParser.airportParser(tripRequest.getDeparture()) + "&destination=" + csvParser.airportParser(tripRequest.getDestination()) +
                        "&depart_date=" + lastYearDep +
                        "&currency=" + tripRequest.getCurrency() +
                        "&return_date=" + lastYearDep +
                        "&limit=" + 34 +
                        "&token=";

        return connectUrlString + Flight_KEY_;

    }
}
