package com.flightradar.flightradar.repository;


import com.flightradar.flightradar.model.currency.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
      Currency findByName (String name);


}

