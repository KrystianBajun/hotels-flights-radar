package com.flightradar.flightradar.repository;


import com.flightradar.flightradar.model.trip.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

  List<Reservation> findByPaid  (boolean paid);
  Reservation findByReservationNumber (String reservationNumber);

}
