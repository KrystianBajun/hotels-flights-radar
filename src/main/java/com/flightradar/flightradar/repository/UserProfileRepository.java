package com.flightradar.flightradar.repository;

import com.flightradar.flightradar.model.user.User;
import com.flightradar.flightradar.model.user.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile,Long> {

    User findByUser(User user);

}
