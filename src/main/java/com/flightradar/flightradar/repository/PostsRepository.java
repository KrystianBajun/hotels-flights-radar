package com.flightradar.flightradar.repository;

import com.flightradar.flightradar.model.user.Posts;
import com.flightradar.flightradar.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository  extends CrudRepository<Posts,Long> {
    List<Posts> findByUser(User user);

}