
package com.flightradar.flightradar.model.currency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class CurrencyData {


    @SerializedName("rates")
    @Expose
    private Map<String, BigDecimal> rates = new HashMap<>();
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("date")
    @Expose
    private String date;


    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }





    @Override
    public String toString() {
        return "CurrencyData{" +
                "rates=" + rates +
                ", base='" + base + '\'' +
                ", date='" + '\'' +
                '}';
    }



}
