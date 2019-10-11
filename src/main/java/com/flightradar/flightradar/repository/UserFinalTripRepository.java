package com.flightradar.flightradar.repository;

import com.flightradar.flightradar.model.user.User;
import com.flightradar.flightradar.model.trip.UserFinalTrip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFinalTripRepository extends CrudRepository<UserFinalTrip,Long> {
        UserFinalTrip findByflightId(Long id);

        UserFinalTrip findByUser_Id(Long id);

        List<UserFinalTrip> findByUser(User user);

        void deleteById(Long id);
}
