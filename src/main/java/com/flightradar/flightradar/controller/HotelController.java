package com.flightradar.flightradar.controller;

import com.flightradar.flightradar.model.comments.HotelComments;
import com.flightradar.flightradar.model.trip.hotel.Hotel;
import com.flightradar.flightradar.model.trip.hotel.HotelRating;
import com.flightradar.flightradar.model.user.User;
import com.flightradar.flightradar.model.trip.Reservation;
import com.flightradar.flightradar.repository.HotelCommentsRepository;
import com.flightradar.flightradar.repository.HotelRatingRepository;
import com.flightradar.flightradar.repository.HotelRepository;
import com.flightradar.flightradar.repository.ReservationRepository;
import com.flightradar.flightradar.security.AllowedForUsers;
import com.flightradar.flightradar.service.hotel.HotelRateService;
import com.flightradar.flightradar.util.HotelNotFoundException;
import com.flightradar.flightradar.util.ReservationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

import static com.flightradar.flightradar.service.Static.CurrentUser;

@Controller
@RequestMapping(value = "/hotels")
public class HotelController {

    @Autowired
    private
    HotelRepository hotelRepository;
    @Autowired
    private HotelRateService hotelRateService;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private HotelCommentsRepository hotelCommentsRepository;
    @Autowired
    private HotelRatingRepository hotelRatingRepository;

    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public String hotelDetails(@PathVariable("id") long id, Model model) {

        Hotel hotel = hotelRepository.findById(id).orElse(null);
        if (hotel == null) {
            throw new HotelNotFoundException();
        }
        List<HotelComments> comments = hotelCommentsRepository.findByHotel(hotel);
        model.addAttribute("hotels", hotel);
        model.addAttribute("comments", comments);

        return "hotel/hotel-details";
    }

    @RequestMapping(value = "/new/comment/{id}", method = RequestMethod.GET)
    @AllowedForUsers
    public String createComment(@PathVariable("id") long id, Model model) {

        Hotel hotel = hotelRepository.findById(id).orElse(null);
        model.addAttribute("hotelComments", new HotelComments());
        model.addAttribute("hotel", hotel);

        return "hotel/hotel-add-comment";
    }

    @RequestMapping(value = "add/comment/{id}", method = RequestMethod.POST)
    public String addComment(@PathVariable("id") long id, @Valid HotelComments hotelComments, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "/new/comment/{id}";
        }
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        if (hotel == null) {
            throw new HotelNotFoundException();
        }
        User user = CurrentUser().getUser();
        hotelComments.setHotel(hotel);
        hotelComments.setUser(user);

        hotelCommentsRepository.save(hotelComments);

        redirectAttributes.addAttribute("id", hotel.getId());

        return "redirect:/hotels/show/{id}";
    }

    @RequestMapping(value = "rate/{id}/{score}/{reservation}", method = RequestMethod.GET)
    public String hotelRate(@PathVariable("id") long id, @PathVariable("score") double score, @PathVariable("reservation")
            String reservationNumber, RedirectAttributes redirectAttributes) {

        Hotel hotel = hotelRepository.findById(id).orElse(null);
        if (hotel == null) {
            throw new HotelNotFoundException();
        }
        Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber);
        if (reservation == null) {
            throw new ReservationNotFoundException();
        }

        if (!reservation.isRated()) {
            reservation.setRated(true);
            reservationRepository.save(reservation);
            HotelRating hotelRating = hotel.getHotelRating();
            Integer votes = hotelRating.getVotesNumber();
            Double average = hotelRating.getAverage();
            Double averageRate = hotelRateService.calculateAverageRate(votes, average, score);

            if (averageRate >= 1.0 || averageRate <= 5.0) {
                hotelRating.setAverage(averageRate);
                hotel.setHotelRating(hotelRating);
                hotelRepository.save(hotel);
            }
        }
        redirectAttributes.addAttribute("id", hotel.getId());

        return "redirect:/new/comment/{id}";
    }

    @RequestMapping(value = "add-comment/{id}/{reservation}", method = RequestMethod.GET)
    public String addComment(@PathVariable("id") long id, Model model) {

        Hotel hotel = hotelRepository.findById(id).orElse(null);
        if (hotel == null) {
            throw new HotelNotFoundException();
        }

        List<HotelComments> comments = hotelCommentsRepository.findByHotel(hotel);
        model.addAttribute("hotels", hotel);
        model.addAttribute("comments", comments);

        return "hotel/hotel-details";
    }

    @RequestMapping(value = "/list/top", method = RequestMethod.GET)
    public String hotelList(Model model) {
        model.addAttribute("hotels",hotelRatingRepository.findAllByOrderByAverageDesc());

        return "hotel/hotel-list";
    }


}
