package com.flightradar.flightradar.model.trip.hotel;

import com.flightradar.flightradar.model.trip.flight.Flight;
import com.flightradar.flightradar.model.comments.HotelComments;
import com.flightradar.flightradar.model.trip.UserFinalTrip;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
@Component
@Table(name = "hotel", uniqueConstraints = @UniqueConstraint(columnNames = {"site", "webRating", "phone", "name", "longitude", "latitude", "image3", "image2", "image", "hotelLink", "describe", "currency", "category", "address", "price"}))

public class Hotel {
    private Long id;
    private String name;
    private String currency;
    private String site;
    private BigDecimal price;
    private String address;
    private Double webRating;
    private String image;
    private String image2;
    private String image3;
    private String image4;

    private String hotelLink;
    private String phone;
    private String describe;
    private String latitude;
    private String longitude;
    private String category;


    @OneToOne(mappedBy = "hotel")
    private Flight flight;

    @OneToOne(mappedBy = "hotel")
    UserFinalTrip userFinalTrip;


    private HotelRating hotelRating;


    private Map<Long, HotelComments> hotelComments = new HashMap<Long, HotelComments>();


    public Hotel() {
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getImage4() {
        return image4;
    }

    public Double getWebRating() {
        return webRating;
    }

    public void setWebRating(Double webRating) {
        this.webRating = webRating;
    }

    public String getHotelLink() {
        return hotelLink;
    }

    public void setHotelLink(String hotelLink) {
        this.hotelLink = hotelLink;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(columnDefinition = "LONGTEXT")
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_rating_id", referencedColumnName = "id")
    public HotelRating getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(HotelRating hotelRating) {
        this.hotelRating = hotelRating;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hotel", orphanRemoval = true)
    @MapKeyColumn(name = "id")
    public Map<Long, HotelComments> getComments() {
        return hotelComments;
    }

    public void setComments(Map<Long, HotelComments> comments) {
        this.hotelComments = comments;
    }
}
