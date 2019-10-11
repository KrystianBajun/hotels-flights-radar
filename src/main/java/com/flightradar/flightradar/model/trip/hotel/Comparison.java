
package com.flightradar.flightradar.model.trip.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comparison {

    @SerializedName("vendor1")
    @Expose
    private String vendor1;
    @SerializedName("vendor2")
    @Expose
    private String vendor2;
    @SerializedName("vendor3")
    @Expose
    private String vendor3;
    @SerializedName("vendor1-price")
    @Expose
    private String vendor1Price;
    @SerializedName("vendor2-price")
    @Expose
    private String vendor2Price;
    @SerializedName("vendor3-price")
    @Expose
    private String vendor3Price;
    @SerializedName("Best-price")
    @Expose
    private String bestPrice;
    @SerializedName("Hotel")
    @Expose
    private String hotel;

    public String getVendor3() {
        return vendor3;
    }

    public void setVendor3(String vendor3) {
        this.vendor3 = vendor3;
    }

    public String getVendor2Price() {
        return vendor2Price;
    }

    public void setVendor2Price(String vendor2Price) {
        this.vendor2Price = vendor2Price;
    }

    public String getVendor1Price() {
        return vendor1Price;
    }

    public void setVendor1Price(String vendor1Price) {
        this.vendor1Price = vendor1Price;
    }

    public String getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(String bestPrice) {
        this.bestPrice = bestPrice;
    }

    public String getVendor1() {
        return vendor1;
    }

    public void setVendor1(String vendor1) {
        this.vendor1 = vendor1;
    }

    public String getVendor3Price() {
        return vendor3Price;
    }

    public void setVendor3Price(String vendor3Price) {
        this.vendor3Price = vendor3Price;
    }

    public String getVendor2() {
        return vendor2;
    }

    public void setVendor2(String vendor2) {
        this.vendor2 = vendor2;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Comparison{" +
                "vendor3='" + vendor3 + '\'' +
                ", vendor2Price='" + vendor2Price + '\'' +
                ", vendor1Price='" + vendor1Price + '\'' +
                ", bestPrice='" + bestPrice + '\'' +
                ", vendor1='" + vendor1 + '\'' +
                ", vendor3Price='" + vendor3Price + '\'' +
                ", vendor2='" + vendor2 + '\'' +
                ", Hotel='" + hotel + '\'' +
                '}';
    }
}
