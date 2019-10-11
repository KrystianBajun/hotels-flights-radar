package com.flightradar.flightradar.repository;

import com.flightradar.flightradar.model.friends.Friendship;
import com.flightradar.flightradar.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends CrudRepository<Friendship, Long> {
    Friendship findByInvitingAndRequester(User inviting, User requester);

    List<Friendship> findByRequester(User requester);

    List<Friendship> findByInvitingOrRequester(User userInviting, User userInvited);
}