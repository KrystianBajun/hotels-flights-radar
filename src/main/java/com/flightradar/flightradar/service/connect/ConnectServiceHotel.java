package com.flightradar.flightradar.service.connect;

import com.flightradar.flightradar.util.ConnectRuntimeException;
import com.flightradar.flightradar.util.OutputFromApiException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class ConnectServiceHotel {

    private String output = null;


    public String connect(String city, String token, String checkIn, String checkOut) {


        try {
            String Request = "https://api.makcorps.com/free/" + city + "/" + checkIn + "/" + checkOut;

            System.out.println("URL String : " + Request);


            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");
            request.header("Authorization", "JWT " + token);
            Response response = request.get("https://api.makcorps.com/free/" + city);


            if (response.getStatusCode() != 200) {

                throw new ConnectRuntimeException("connectServiceFlight Hotel-API, HTTP error code is: ", response.getStatusCode());


            } else {

                BufferedReader in = new BufferedReader(new InputStreamReader(response.asInputStream()));
                String inputLine;
                StringBuffer res = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    res.append(inputLine);
                }
                in.close();

                output = res.toString();
                System.out.println(output);
                System.out.println(response);
                System.out.println(response.getStatusCode());

            }
        } catch (Exception e) {

            throw new OutputFromApiException("connectServiceFlight Hotel-API: output is : ", e.getMessage());
        }


        return output;
    }


    public String connect(String city, String token) {


        try {
            String Request = "https://api.makcorps.com/free/" + city;

            System.out.println("URL String : " + Request);


            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");
            request.header("Authorization", "JWT " + token);
            Response response = request.get("https://api.makcorps.com/free/" + city);


            if (response.getStatusCode() != 200) {

                throw new ConnectRuntimeException("connectServiceFlight Hotel-API, HTTP error code is: ", response.getStatusCode());


            } else {

                BufferedReader in = new BufferedReader(new InputStreamReader(response.asInputStream()));
                String inputLine;
                StringBuffer res = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    res.append(inputLine);
                }
                in.close();

                output = res.toString();
                System.out.println(output);
                System.out.println(response);
                System.out.println(response.getStatusCode());

            }
        } catch (Exception e) {

            throw new OutputFromApiException("connectServiceFlight Hotel-API: output is : ", e.getMessage());
        }


        return output;
    }


}

