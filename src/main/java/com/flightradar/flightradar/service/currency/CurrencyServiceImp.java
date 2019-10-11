package com.flightradar.flightradar.service.currency;

import com.flightradar.flightradar.model.currency.Currency;
import com.flightradar.flightradar.repository.CurrencyRepository;
import com.flightradar.flightradar.service.connect.ConnectServiceCurrency;
import com.flightradar.flightradar.service.gson.GsonConvertImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Component
@Service
public class CurrencyServiceImp implements CurrencyService {
    //Exchange rate in USD

    @Autowired
    private ConnectServiceCurrency connectCurrency;

    @Autowired
    private GsonConvertImp gsonConvert;

    @Autowired
    private CurrencyRepository currencyRepository;

    public void update() {
        String con;
        con = connectCurrency.connect();
        Map<String, BigDecimal> currencyMap = gsonConvert.gsonCurrency(con).getRates();
        for (String key : currencyMap.keySet()) {
            Currency currency = new Currency(key, currencyMap.get(key));
            currencyRepository.deleteAll();
            currencyRepository.save(currency);
        }

    }
}