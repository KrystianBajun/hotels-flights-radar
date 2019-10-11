package com.flightradar.flightradar.repository;

import com.flightradar.flightradar.model.comments.Messages;
import com.flightradar.flightradar.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends CrudRepository<Messages, Long> {

    List<Messages> findByUser(User user);
}
