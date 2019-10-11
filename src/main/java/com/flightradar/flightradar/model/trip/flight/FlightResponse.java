package com.flightradar.flightradar.model.trip.flight;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FlightResponse {


    private boolean success;
    private Map<String, Map<String, FlightDeserialization>> data;
    private String error;
    private String currency;


    public Map<String, FlightDeserialization> getFlatData() {
        return data.entrySet()
                .stream()
                .collect(HashMap::new, (m, e) -> m.putAll(e.getValue()), Map::putAll);


    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, Map<String, FlightDeserialization>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, FlightDeserialization>> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "FlightResponse{" +
                "success=" + success +
                ", data=" + data +
                ", error='" + error + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
