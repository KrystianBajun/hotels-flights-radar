package com.flightradar.flightradar.service.connect;

import com.flightradar.flightradar.util.ConnectRuntimeException;
import com.flightradar.flightradar.util.OutputFromApiException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Component
public class ConnectServiceCurrency {
    private static final String REQUEST_CURRENCY = "https://api.exchangeratesapi.io/latest?base=USD";


    public String connect() {

        String output = null;
        try {

            System.out.println("URL String : " + REQUEST_CURRENCY);

            URL url = new URL(REQUEST_CURRENCY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // If response code !=200 get data from DB
            if (conn.getResponseCode() != 200) {
                throw new ConnectRuntimeException("CurrencyAPI, response code : ", conn.getResponseCode());
            } else {

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                output = response.toString();
            }
        } catch (Exception e) {

            throw new OutputFromApiException("connectServiceFlight CurrencyData-API: output is : ", e.getMessage());
        }

        return output;
    }


}
