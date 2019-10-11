package com.flightradar.flightradar.model.comments;

import com.flightradar.flightradar.model.trip.hotel.Hotel;
import com.flightradar.flightradar.model.user.User;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hotelComments")
@Entity
public class HotelComments {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;

    @Column(name = "enabled")
    private boolean enabled;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "Hotel_Id")
    private Hotel hotel;

    @ManyToOne
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
