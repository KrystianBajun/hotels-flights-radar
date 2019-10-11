package com.flightradar.flightradar.repository;

import com.flightradar.flightradar.model.trip.hotel.Hotel;
import com.flightradar.flightradar.model.trip.hotel.HotelRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HotelRepository extends CrudRepository<Hotel,Long> {

    Hotel findByName (String name);

    List<Hotel>  findByHotelRating (List<HotelRating>  HotelRating);

}
