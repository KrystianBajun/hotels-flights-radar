package com.flightradar.flightradar.controller;

import com.flightradar.flightradar.model.trip.FinalTrip;
import com.flightradar.flightradar.model.trip.flight.Flight;
import com.flightradar.flightradar.model.trip.hotel.Hotel;
import com.flightradar.flightradar.model.user.User;
import com.flightradar.flightradar.repository.FinalTripRepository;
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
    private FinalTripRepository finalTripRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/myList", method = RequestMethod.GET)
    @AllowedForUsers
    public String tripList(Model model) {
        User user = userRepository.findByUsername(CurrentUser().getUser().getUsername());
        model.addAttribute("trips", finalTripRepository.findByUser(user));

        return "final-trip/trip-list";
    }

    @RequestMapping(value = "/myList/remove/{id}", method = RequestMethod.GET)
    @AllowedForUsers
    public String removeTrip(@PathVariable("id") Long id) {
        FinalTrip finalTrip = finalTripRepository.findById(id).orElse(null);
        finalTripRepository.deleteById(id);
        if (finalTrip != null) {
            return "final-trip/trip-list";
        } else
            return "/";
    }


    @RequestMapping(value = "/summary/{id}", method = RequestMethod.GET)
    @AllowedForUsers
    public String tripSummary(@PathVariable("id") Long id, Model model) {
        FinalTrip finalTrip = finalTripRepository.findById(id).orElse(null);
        if (finalTrip == null) {
            throw new FinalTripNotFoundException();
        }
        User user = userRepository.findById(finalTrip.getId());
        if (user == null) {
            throw new UserNotFoundException();
        }

        Flight flight = finalTrip.getFlight();
        Hotel hotel = finalTrip.getHotel();
        String reservationNumber = finalTrip.getReservation().getReservationNumber();

        model.addAttribute("isPaid", finalTrip.getReservation().isPaid());
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

