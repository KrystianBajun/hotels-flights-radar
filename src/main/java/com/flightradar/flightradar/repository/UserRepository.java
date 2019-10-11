package com.flightradar.flightradar.repository;

import com.flightradar.flightradar.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
	User findById(long id);
	List<User> findByEmail (String email);
}
