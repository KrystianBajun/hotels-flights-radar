package com.flightradar.flightradar.service.flight;

import com.flightradar.flightradar.model.trip.TripRequest;
import com.flightradar.flightradar.model.historicalFlight.Historical;
import com.flightradar.flightradar.model.historicalFlight.HistoricalDeserialization;
import com.flightradar.flightradar.repository.HistoricalFlightRepository;
import com.flightradar.flightradar.service.connect.ConnectServiceFlight;
import com.flightradar.flightradar.service.gson.GsonConvert;
import com.flightradar.flightradar.service.url.HistoricalFlightUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static com.flightradar.flightradar.service.Static.CurrentUser;

@Component
@Service
public class HistoricalFlightService  {

    @Autowired
    private GsonConvert gsonConvert;

    @Autowired
    private
    HistoricalFlightUrl historicalFlightUrl;

    @Autowired
    private
    ConnectServiceFlight connectService;

    @Autowired
    private HistoricalFlightRepository historicalRepository;


    public String buildUrl(TripRequest tripRequest) {


        return historicalFlightUrl.url(tripRequest);
    }

    private String buildLastYearUrl(TripRequest tripRequest) {


        return historicalFlightUrl.lastYearUrl(tripRequest);
    }

    private String buildTwoYearsUrl(TripRequest tripRequest) {


        return historicalFlightUrl.twoYearsUrl(tripRequest);
    }

    public Map<String, BigDecimal> calculatePriceDetails(TripRequest tripRequest) {

        String lastYearOutput = connect((buildLastYearUrl(tripRequest)));
        String twoYearsAgoOutput = connect((buildTwoYearsUrl(tripRequest)));

        ArrayList<HistoricalDeserialization> lastYear = gsonConvert.gsonHistorical(lastYearOutput).getData();
        ArrayList<HistoricalDeserialization> twoYearsAgo = gsonConvert.gsonHistorical(twoYearsAgoOutput).getData();

        finalAverage(averageDetailsPrice(lastYear), averageDetailsPrice(twoYearsAgo));
        calculateMedian(sortPrices(lastYear, twoYearsAgo));
        sortPrices(lastYear, twoYearsAgo);

        int cheapestPriceListSize = sortPrices(lastYear, twoYearsAgo).length;

        BigDecimal cheapestPrice = sortPrices(lastYear, twoYearsAgo)[1];
        BigDecimal higestPrice = sortPrices(lastYear, twoYearsAgo)[cheapestPriceListSize - 1];

        Map<String, BigDecimal> priceDetails = new HashMap<>();

        priceDetails.put("average", finalAverage(averageDetailsPrice(lastYear), averageDetailsPrice(twoYearsAgo)));
        priceDetails.put("median", calculateMedian(sortPrices(lastYear, twoYearsAgo)));

        priceDetails.put("higestPrice", higestPrice);
        priceDetails.put("minimalPrice", cheapestPrice);


        return priceDetails;
    }

    private BigDecimal[] sortPrices(ArrayList<HistoricalDeserialization> fromLastYear, ArrayList<HistoricalDeserialization> fromTwoYearsAgo) {

        ArrayList<BigDecimal> pricesList = new ArrayList<>();

        for (HistoricalDeserialization lst : fromLastYear) {
            pricesList.add(lst.getValue());
        }

        for (HistoricalDeserialization lst : fromTwoYearsAgo) {
            pricesList.add(lst.getValue());
        }


        return sortList(pricesList);
    }

    private BigDecimal finalAverage(BigDecimal lastYearAverage, BigDecimal twoYearsAgoAverage) {


        BigDecimal two = new BigDecimal("2");


        return lastYearAverage.add(twoYearsAgoAverage).divide(two, RoundingMode.HALF_UP);
    }

    private BigDecimal averageDetailsPrice(ArrayList<HistoricalDeserialization> list) {

        BigDecimal sum = new BigDecimal("0");
        BigDecimal days = new BigDecimal("0");
        MathContext mc = new MathContext(3);

        BigDecimal average;

        for (HistoricalDeserialization lst : list) {
            String[] split = lst.getDepart_date().split("-");
            int year = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]);
            YearMonth yearMonthObject = YearMonth.of(year, month);

            int daysInMonth = yearMonthObject.lengthOfMonth();
            days = BigDecimal.valueOf(daysInMonth);
            sum = sum.add(lst.getValue());

        }

        average = sum.divide(days, RoundingMode.HALF_UP);

        return average.round(mc);
    }

    private BigDecimal calculateMedian(BigDecimal[] numArray) {

        BigDecimal median;
        BigDecimal two = new BigDecimal("2");
        if (numArray.length % 2 == 0)

            median = (numArray[numArray.length/2].add (numArray[numArray.length/2 - 1]).divide(two,RoundingMode.DOWN));
        else
            median =  numArray[numArray.length/2];

        return median;
    }

    public String connect(String url) {

        return connectService.connect(url);
    }

    public void save(String output, TripRequest tripRequest) {

        ArrayList<HistoricalDeserialization> list = gsonConvert.gsonHistorical(output).getData();

        Historical historical;

        for (HistoricalDeserialization aList : list) {


            historical = new Historical();

            historical.setAirline(aList.getGate());
            historical.setOrigin(aList.getOrigin());
            historical.setDestination(aList.getDestination());
            historical.setPrice(aList.getValue());
            historical.setTripClass(aList.getTrip_class());
            historical.setDepartDate(aList.getDepart_date());
            historical.setReturnDate(aList.getReturn_date());

            historical.setCurrency(tripRequest.getCurrency());

            historical.setUserName(CurrentUser().getUsername());

            historicalRepository.save(historical);

        }


    }

    public BigDecimal[] sortList(ArrayList<BigDecimal> list) {

        list.sort(Comparator.naturalOrder());

        return arrayListToArray(list);
    }

    private BigDecimal[] arrayListToArray(ArrayList<BigDecimal> list) {

        BigDecimal[] array = new BigDecimal[list.size()];


        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);

        }
            return array;
    }

public BigDecimal averagePrice(ArrayList<Historical> list) {

        BigDecimal sum = new BigDecimal("0");
        BigDecimal days = new BigDecimal("0");
        MathContext mc = new MathContext(3);

        BigDecimal average;

        for (Historical his : list) {
            String[] split = his.getDepartDate().split("-");
            int year = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]);
            YearMonth yearMonthObject = YearMonth.of(year, month);

            int daysInMonth = yearMonthObject.lengthOfMonth();
            days = BigDecimal.valueOf(daysInMonth);
            sum = sum.add(his.getPrice());

        }

        average = sum.divide(days, RoundingMode.DOWN);

        return average.round(mc);

    }


}

