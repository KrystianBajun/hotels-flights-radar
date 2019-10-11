package com.flightradar.flightradar;

import com.flightradar.flightradar.model.currency.Currency;
import com.flightradar.flightradar.model.user.User;
import com.flightradar.flightradar.repository.CurrencyRepository;
import com.flightradar.flightradar.repository.UserRepository;
import com.flightradar.flightradar.security.SecurityConfiguration;
import com.flightradar.flightradar.service.currency.CurrencyService;
import com.flightradar.flightradar.service.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;

@SpringBootApplication
public class FlightRadarApplication implements CommandLineRunner {
    @Autowired
    private UserRepository users;


    private CurrencyRepository currencyRepository;
    private Currency currency = new Currency();

    public class WebApplication extends SpringBootServletInitializer {


        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            currencyRepository.save(currency);
            return application.sources(FlightRadarApplication.class);
        }
    }

    public static void main(String[] args) {

        SpringApplication.run(FlightRadarApplication.class, args);

    }

    @Override
    public void run(String... strings) {
        Iterator<User> it = users.findAll().iterator();

        while (it.hasNext()) {
            User newUser = it.next();
            String pass = newUser.getPassword();
            newUser.setPassword(SecurityConfiguration.encoder.encode(pass));
            users.save(newUser);
        }
    }

    @Autowired
    private
    CurrencyService currencyService;

    @Scheduled(initialDelay = 1000L, fixedDelayString = "PT10M")
    void currencyConnect() throws InterruptedException, IOException {
        currencyService.update();
    }

    @Autowired
    private EmailSender emailSender;

    //PT14400M
    @Scheduled(initialDelay = 1000L, fixedDelayString = "PT1M")
    void scheduleEmail() throws InterruptedException, IOException, ParseException {
        emailSender.isTripFinishedAndPaid();
    }


    @Configuration
    @EnableScheduling
    @ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
    class SchedulingConfiguraton {


    }


}









