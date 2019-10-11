package com.flightradar.flightradar.model.trip;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {
    private long id;
    private String reservationNumber;
    private boolean paid;
    private boolean rated;

    @OneToOne(mappedBy = "reservation")
    UserFinalTrip userFinalTrip;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    @Column(name = "paid")
    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Column(name = "rated")
    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }
}
