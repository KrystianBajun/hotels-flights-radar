package com.flightradar.flightradar.controller;

import com.flightradar.flightradar.model.trip.UserFinalTrip;
import com.flightradar.flightradar.model.trip.flight.Flight;
import com.flightradar.flightradar.model.trip.hotel.Hotel;
import com.flightradar.flightradar.model.user.User;
import com.flightradar.flightradar.repository.UserFinalTripRepository;
import com.flightradar.flightradar.repository.UserRepository;
import com.flightradar.flightradar.security.AllowedForUsers;
import com.flightradar.flightradar.util.FinalTripNotFoundException;
import com.flightradar.flightradar.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.flightradar.flightradar.service.Static.CurrentUser;

@Controller
@RequestMapping(value = "/final/trip")

public class FinalTripController {
    @Autowired
    private UserFinalTripRepository userFinalTripRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/myList", method = RequestMethod.GET)
    @AllowedForUsers
    public String tripList(Model model) {
        User user = userRepository.findByUsername(CurrentUser().getUser().getUsername());
        model.addAttribute("trips", userFinalTripRepository.findByUser(user));

        return "final-trip/trip-list";
    }

    @RequestMapping(value = "/myList/remove/{id}", method = RequestMethod.GET)
    @AllowedForUsers
    public String removeTrip(@PathVariable("id") Long id) {
        UserFinalTrip userFinalTrip = userFinalTripRepository.findById(id).orElse(null);
        userFinalTripRepository.deleteById(id);
        if (userFinalTrip != null) {
            return "final-trip/trip-list";
        } else
            return "/";
    }


    @RequestMapping(value = "/summary/{id}", method = RequestMethod.GET)
    @AllowedForUsers
    public String tripSummary(@PathVariable("id") Long id, Model model) {
        UserFinalTrip userFinalTrip = userFinalTripRepository.findById(id).orElse(null);
        if (userFinalTrip == null) {
            throw new FinalTripNotFoundException();
        }
        User user = userRepository.findById(userFinalTrip.getId());
        if (user == null) {
            throw new UserNotFoundException();
        }

        Flight flight = userFinalTrip.getFlight();
        Hotel hotel = userFinalTrip.getHotel();
        String reservationNumber = userFinalTrip.getReservation().getReservationNumber();

        model.addAttribute("isPaid", userFinalTrip.getReservation().isPaid());
        model.addAttribute("hotels", hotel);
        model.addAttribute("flights", flight);
        model.addAttribute("reservationNumber", reservationNumber);
        //chart-> js. attribute
        model.addAttribute("currency", flight.getCurrency());
        model.addAttribute("median", flight.getMedian());
        model.addAttribute("minimalPrice", flight.getMinimalPrice());
        model.addAttribute("highestPrice", flight.getHighestPrice());
        model.addAttribute("price", flight.getPrice());
        model.addAttribute("average", flight.getAverage());
        //google-maps-> js. attribute
        model.addAttribute("latitude", hotel.getLatitude());
        model.addAttribute("longitude", hotel.getLongitude());

        model.addAttribute("users", user);

        return "users/trip-summary";
    }
}

