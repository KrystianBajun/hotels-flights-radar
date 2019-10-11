package com.flightradar.flightradar.repository;

import com.flightradar.flightradar.model.comments.HotelComments;
import com.flightradar.flightradar.model.trip.hotel.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelCommentsRepository extends CrudRepository<HotelComments, Long> {
   List<HotelComments>  findByHotel (Hotel hotel);
   List<HotelComments>  findByHotelId (Hotel hotel);



}
