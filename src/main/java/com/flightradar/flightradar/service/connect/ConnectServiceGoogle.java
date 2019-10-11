package com.flightradar.flightradar.service.connect;


import com.flightradar.flightradar.util.ConnectRuntimeException;
import com.flightradar.flightradar.util.OutputFromApiException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class ConnectServiceGoogle {
    public String connectPlace(String urlRequest) {

        String output;

        try {
            System.out.println("URL Google-place request : " + urlRequest);

            URL url = new URL(urlRequest);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new ConnectRuntimeException("GoogleApi place, response code : ", conn.getResponseCode());
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
            throw new OutputFromApiException("ConnectServiceGoogle , output is : ", e.getMessage());
        }

        return output;
    }

    public String connectImage(String urlRequest) {

        String output;
        try {
            System.out.println("URL Google-place request : " + urlRequest);
            URL url = new URL(urlRequest);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new ConnectRuntimeException("GoogleApi image, response code : ", conn.getResponseCode());
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
            throw new OutputFromApiException("ConnectServiceGoogle , output is : ", e.getMessage());
        }
        return output;
    }


}
