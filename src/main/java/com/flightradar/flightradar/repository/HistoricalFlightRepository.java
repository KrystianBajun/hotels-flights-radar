package com.flightradar.flightradar.repository;

import com.flightradar.flightradar.model.historicalFlight.Historical;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
@Transactional
public interface HistoricalFlightRepository extends CrudRepository<Historical,Long> {


  ArrayList<Historical> findByUserName(String username);
  Long removeByUserName(String UserName);
}
