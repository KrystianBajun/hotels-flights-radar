package com.flightradar.flightradar.model.hotelGoogle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Places
{
    private String snippet;

    private String[] extensions;

    private String address;

    private String phone;

    private String link;

    private Double rating;

    private String sponsored;

    private String position;

    private String title;

    private String category;

    private GpsCoordinates gps_coordinates;

    public String getSnippet ()
    {
        return snippet;
    }

    public void setSnippet (String snippet)
    {
        this.snippet = snippet;
    }

    public String[] getExtensions ()
    {
        return extensions;
    }

    public void setExtensions (String[] extensions)
    {
        this.extensions = extensions;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public Double getRating ()
    {
        return rating;
    }

    public void setRating (Double rating)
    {
        this.rating = rating;
    }

    public String getSponsored ()
    {
        return sponsored;
    }

    public void setSponsored (String sponsored)
    {
        this.sponsored = sponsored;
    }

    public String getPosition ()
    {
        return position;
    }

    public void setPosition (String position)
    {
        this.position = position;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public GpsCoordinates getGps_coordinates() {
        return gps_coordinates;
    }

    public void setGps_coordinates(GpsCoordinates gps_coordinates) {
        this.gps_coordinates = gps_coordinates;
    }

    @Override
    public String toString() {
        return "Places{" +
                "snippet='" + snippet + '\'' +
                ", extensions=" + Arrays.toString(extensions) +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", link='" + link + '\'' +
                ", rating=" + rating +
                ", sponsored='" + sponsored + '\'' +
                ", position='" + position + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", gps_coordinates=" + gps_coordinates +
                '}';
    }
}
