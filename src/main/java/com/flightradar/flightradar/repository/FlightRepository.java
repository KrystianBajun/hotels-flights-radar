package com.flightradar.flightradar.repository;

import com.flightradar.flightradar.model.trip.flight.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional

public interface FlightRepository extends CrudRepository<Flight, Long>{
        Flight findByDeparture(String departure);
        Flight findByDestination(String destination);
        Flight findByAirlineIata(String airlineIata);
        List<Flight>  findByReturnTime(Date returnTime);
        List<Flight> findByUserName(String username);


}
