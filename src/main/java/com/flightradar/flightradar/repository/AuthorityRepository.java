package com.flightradar.flightradar.repository;

import com.flightradar.flightradar.model.user.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    Authority findByRole(String role);
}

