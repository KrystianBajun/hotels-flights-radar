package com.flightradar.flightradar.repository;

import com.flightradar.flightradar.model.trip.hotel.HotelRating;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelRatingRepository extends CrudRepository<HotelRating, Long> {

    List<HotelRating> findAllByOrderByAverageDesc();

}
