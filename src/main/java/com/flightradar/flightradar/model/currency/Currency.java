package com.flightradar.flightradar.model.currency;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Component
@Table(name = "CURRENCY")
public class Currency {


    private Long id;
    private String name;
    private BigDecimal exchangeRate;



    public Currency() {
    }

    public Currency(String name, BigDecimal exchangeRate) {
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "EXCHANGE_RATE")
    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}