package com.flightradar.flightradar.service.hotel;

import com.flightradar.flightradar.util.OutputFromApiException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
@Component
public class HotelGetToken {
String token;

    public String getToken() {
        try {
            JSONObject requestBody = new JSONObject();
            requestBody.put("username", "ahojprzygodo");
            requestBody.put("password", "Airmax90");
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");
            request.body(requestBody.toString());
            Response response = (Response) request.post("https://api.makcorps.com/auth").getBody();


            BufferedReader in = new BufferedReader(new InputStreamReader(response.asInputStream()));
            String inputLine;
            StringBuffer response1 = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response1.append(inputLine);
            }
            in.close();


            token = response1.toString();





        } catch (Exception e) {
            throw new OutputFromApiException("connectServiceFlight Hotel-API: output is : ", e.getMessage());

        }

        System.out.println(token);
        return token;
    }







}
