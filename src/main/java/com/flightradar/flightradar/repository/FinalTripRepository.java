package com.flightradar.flightradar.repository;

import com.flightradar.flightradar.model.user.User;
import com.flightradar.flightradar.model.trip.FinalTrip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinalTripRepository extends CrudRepository<FinalTrip,Long> {
        FinalTrip findByflightId(Long id);

        FinalTrip findByUser_Id(Long id);

        List<FinalTrip> findByUser(User user);

        void deleteById(Long id);
}
