package com.flightradar.flightradar.model.historicalFlight;

import java.util.ArrayList;

public class HistoricalResponse {
    private ArrayList<HistoricalDeserialization> data;

    private String success;

    public ArrayList<HistoricalDeserialization> getData() {
        return data;
    }

    public void setData(ArrayList<HistoricalDeserialization> data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ClassPojo [data = " + data + ", success = " + success + "]";
    }
}
			
			