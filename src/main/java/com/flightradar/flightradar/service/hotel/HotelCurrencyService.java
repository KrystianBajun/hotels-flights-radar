package com.flightradar.flightradar.service.hotel;


import org.springframework.stereotype.Component;

import java.math.BigDecimal;


// Output from Hotel API is always in USD :
// in format for example: "US$96"
// class provides the current updated price in the selected currency
@Component
public class HotelCurrencyService {

    public BigDecimal amountCalculator(String currencyAndAmount, BigDecimal currency) {

        if (currencyAndAmount == null) {
            return BigDecimal.valueOf(0);
        } else if (currency == null) {
            String[] parts = currencyAndAmount.split("\\$");
            String dollars = parts[1];
            return parseAmount(dollars);

        } else {
            String[] parts = currencyAndAmount.split("\\$");
            String amount = parts[1];

            return currency.multiply(parseAmount(amount));
        }
    }

    private BigDecimal parseAmount(String currency) {

        int AmountToInt = Integer.parseInt(currency);

        return new BigDecimal(AmountToInt);
    }


}

