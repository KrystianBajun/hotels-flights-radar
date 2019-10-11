package com.flightradar.flightradar.controller;


import com.flightradar.flightradar.model.trip.hotel.HotelRequest;
import com.flightradar.flightradar.model.trip.hotel.Hotel;
import com.flightradar.flightradar.repository.HotelRepository;
import com.flightradar.flightradar.repository.UserRepository;
import com.flightradar.flightradar.security.AllowedForAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository users;

    //Todo in User class also are admin privileges
    @RequestMapping(value = "/addHotel", method = RequestMethod.GET)
    @AllowedForAdmin
    public ModelAndView newHotel() {
        return new ModelAndView("hotel/hotel-add-new", "HotelRequest", new HotelRequest());
    }

    @RequestMapping(value = "/hotelAdd", method = RequestMethod.POST)
    @AllowedForAdmin
    public String addHotel(@ModelAttribute("HotelRequest") HotelRequest hotelRequest,
                           BindingResult result) {

        final String currency = "USD";
        Hotel hotel = new Hotel();
        hotel.setPrice(hotelRequest.getPrice());
        hotel.setCurrency(currency);
        hotel.setName(hotelRequest.getName());
        hotel.setSite(hotelRequest.getSite());
        hotel.setAddress(hotelRequest.getAddress());
        hotelRepository.save(hotel);
        if (result.hasErrors()) {
            return "error";
        } else
            return "/loading";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    @AllowedForAdmin
    public String manageUsers(Model model) {
        model.addAttribute("users", users.findAll());
        return "admin/users-list";
    }
}