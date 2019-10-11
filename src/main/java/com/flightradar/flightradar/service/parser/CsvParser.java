package com.flightradar.flightradar.service.parser;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import java.util.Map;

@Service
@Component
public class CsvParser {
//Class responsible for parse IATA codes to readable full name, user friendly

    public String airlineParser(String airline) {

        String csvFile = "C:\\Users\\Krystian\\Desktop\\Flight Radar\\flight-radar\\src\\main\\resources\\csv\\airlines.csv";
        String line = "";
        String cvsSplitBy = ";";

        Map<String, String> parserMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {


                String[] airlinesplit = line.split(cvsSplitBy);
                parserMap.put(airlinesplit[0], airlinesplit[1]);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return parserMap.get(airline);
    }


    public String airportParser(String airport) {
        String csvFile = "C:\\Users\\Krystian\\Desktop\\Flight Radar\\flight-radar\\src\\main\\resources\\csv\\airport.csv";
        String line = "";
        String cvsSplitBy = ",";
        Map<String, String> parserMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                String newString = line.replace("\"", "");

                String[] airportSplit = newString.split(cvsSplitBy);

                parserMap.put(airportSplit[0], airportSplit[2]);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return parserMap.get(airport);

    }

}







