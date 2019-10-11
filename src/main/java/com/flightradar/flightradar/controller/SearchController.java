package com.flightradar.flightradar.controller;

import com.flightradar.flightradar.model.hotelGoogle.Images;
import com.flightradar.flightradar.model.hotelGoogle.Places;
import com.flightradar.flightradar.model.trip.TripRequest;
import com.flightradar.flightradar.model.trip.flight.Flight;
import com.flightradar.flightradar.model.trip.flight.FlightDeserialization;
import com.flightradar.flightradar.model.trip.hotel.Comparison;
import com.flightradar.flightradar.model.trip.hotel.Hotel;
import com.flightradar.flightradar.model.user.User;
import com.flightradar.flightradar.model.trip.Reservation;
import com.flightradar.flightradar.model.trip.FinalTrip;
import com.flightradar.flightradar.repository.FlightRepository;
import com.flightradar.flightradar.repository.HotelRepository;
import com.flightradar.flightradar.repository.FinalTripRepository;
import com.flightradar.flightradar.repository.UserRepository;
import com.flightradar.flightradar.security.AllowedForUsers;
import com.flightradar.flightradar.service.connect.ConnectServiceFlight;
import com.flightradar.flightradar.service.connect.ConnectServiceHotel;
import com.flightradar.flightradar.service.flight.FlightService;
import com.flightradar.flightradar.service.flight.HistoricalFlightService;
import com.flightradar.flightradar.service.gson.GsonConvert;
import com.flightradar.flightradar.service.hotel.google.HotelGService;
import com.flightradar.flightradar.service.url.CheapFlightURL;
import com.flightradar.flightradar.util.FlightNotFoundException;
import com.flightradar.flightradar.util.ResponseIsEmptyException;
import com.flightradar.flightradar.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

import static com.flightradar.flightradar.service.Static.CurrentUser;
import static com.flightradar.flightradar.service.Static.RandomGenerator;


@Component
@Controller
public class SearchController {

    @Autowired
    FlightService tripService;

    @Autowired
    HistoricalFlightService historicalService;

    @Autowired
    private
    ConnectServiceFlight connectServiceFlight;

    @Autowired
    private
    CheapFlightURL buildUrl;

    @Autowired
    private
    FlightRepository flightRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private GsonConvert gsonConvert;

    @Autowired
    UserRepository users;

    @Autowired
    private
    FinalTripRepository finalTripRepository;

    @Autowired
    FinalTrip finalTrip;

    @Autowired
    private
    UserRepository userRepository;

    @Autowired
    private HotelGService hotelGService;
    @Autowired
    private ConnectServiceHotel connectServiceHotel;

    @RequestMapping(value = "/Search", method = RequestMethod.GET)
    @AllowedForUsers
    public ModelAndView showSearchPage() {
        return new ModelAndView("main-search-page", "TripRequest", new TripRequest());
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String searchFlightAndHotel(@Valid @ModelAttribute("TripRequest") TripRequest tripRequest) {

        String flightOutput = connectServiceFlight.connect(buildUrl.url(tripRequest));

        boolean emptyFlightResponse = gsonConvert.gsonFlight(flightOutput).getData().size() == 0;

        if (emptyFlightResponse) {

            throw new ResponseIsEmptyException();
        }

        Map<String, BigDecimal> priceStatistics = historicalService.calculatePriceDetails(tripRequest);
        Map<String, Map<String, FlightDeserialization>> flightOutputMap = gsonConvert.gsonFlight(flightOutput).getData();


        // Simulating hotel search
        //  String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NjI3NTExMjQsImV4cCI6MTU2Mjc1MjkyNCwiaWRlbnRpdHkiOjQ4NywiaWF0IjoxNTYyNzUxMTI0fQ.e8woy3ko1s2unitb6To9iR_uUoV6RV6jRd0e_pnewZQ";

        //   String request = null;
        try {
            //    request = connectServiceHotel.connectPlace(tripRequest.getDeparture(),token,tripRequest.getDateOut(),tripRequest.getDateBack());

            //      System.out.println(request);
      /*  User user = userRepository.findById(id);
            if (user == null)
                throw new UserNotFoundException();*/

        } catch (Exception e) {
            System.out.println("Invalid data can not build an URL" + e);
        }

        Comparison[] comparisionHotel = gsonConvert.gsonHotel("TEST").getComparison();

        ArrayList<Comparison> hotelOutput = new ArrayList<>(Arrays.asList(comparisionHotel));

        boolean emptyHotelGResponse = hotelOutput.size() == 0;

        if (emptyHotelGResponse) {
            throw new ResponseIsEmptyException();
        }
        Map<Integer, List<Places>> hotelGPlaceList = hotelGService.getHotelGPlaces(hotelOutput);
        Map<Integer, List<Images>> hotelGImageList = hotelGService.getHotelGImages(hotelOutput);


/*
        try {
*/  //todo exception
        tripService.tripSave(flightOutputMap, tripRequest, hotelGPlaceList, hotelGImageList, hotelOutput, priceStatistics);
        /*} catch (Exception e) {
            System.out.println("Already in database : " + e);
        }*/

        return "redirect:list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @AllowedForUsers
    public String list(Model model) {

        List<Flight> flight = flightRepository.findByUserName(CurrentUser().getUsername());
        if (flight == null) {
            throw new FlightNotFoundException("No such flight found !");
        }
        User user = userRepository.findById(CurrentUser().getUser().getId());
        model.addAttribute("flight", flight);
        model.addAttribute("users", user);
        return "users/search-result";
    }

    @RequestMapping(value = "{flight.id}/chooseFlight", method = RequestMethod.GET)
    @AllowedForUsers
    public String chooseFlight(@PathVariable("flight.id") long flightId, RedirectAttributes redirectAttributes) {

        User user = userRepository.findById(CurrentUser().getUser().getId());
        FinalTrip finalTrip = new FinalTrip();


        Long passId = flightId;
        redirectAttributes.addFlashAttribute("passFlight", passId);
        return "redirect:/hotelList";
    }

    @RequestMapping(value = "hotelList", method = RequestMethod.GET)
    @AllowedForUsers
    public String hotelList(Model model, @ModelAttribute("passFlight") long passId) {

        User user = userRepository.findById(CurrentUser().getUser().getId());
        if (user == null) {
            throw new UserNotFoundException();
        }
        model.addAttribute("flight", passId);
        model.addAttribute("hotels", hotelRepository.findAll());
        model.addAttribute("users", user);

        return "users/hotel-search-result";
    }

    @RequestMapping(value = "{hotel.id}/{flight.id}/chooseHotel", method = RequestMethod.GET)
    @AllowedForUsers
    public ModelAndView chooseHotel(@PathVariable("hotel.id") long hotelId, @PathVariable("flight.id") long flightId) {

        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        Flight flight = flightRepository.findById(flightId).orElse(null);


        User userTemp = userRepository.findById(CurrentUser().getUser().getId());
        FinalTrip finalTrip = new FinalTrip();
        Reservation reservation = new Reservation();

        reservation.setReservationNumber(RandomGenerator());
        finalTrip.setReservation(reservation);


        BigDecimal flightPrice = flightRepository.findById(flightId).get().getPrice();
        BigDecimal hotelPrice = hotelRepository.findById(hotelId).get().getPrice();
        BigDecimal totalPrice = flightPrice.add(hotelPrice);

        finalTrip.setFlight(flight);
        finalTrip.setHotel(hotel);

        finalTrip.setTotalPrice(totalPrice);
        finalTrip.setReservation(reservation);

        userTemp.add(finalTrip);

        finalTripRepository.save(finalTrip);


        final ModelAndView redirect = new ModelAndView("redirect:/final/trip/myList");


        return redirect;
    }
}















