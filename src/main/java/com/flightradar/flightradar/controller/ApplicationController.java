package com.flightradar.flightradar.controller;

import com.flightradar.flightradar.model.user.CustomUserDetail;
import com.flightradar.flightradar.repository.*;
import com.flightradar.flightradar.service.friends.FriendsService;
import com.flightradar.flightradar.service.hotel.HotelRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    @Autowired
    private FlightRepository flights;

    @Autowired
    private BlogRepository blogs;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private HotelRateService hotelRateService;

    @RequestMapping("/")
    public String home(Model model) {

        model.addAttribute("hotels", hotelRateService.topHotels());
        model.addAttribute("flight", flights.findAll());
        model.addAttribute("blogs", blogs.findAll());
        model.addAttribute("notifications", friendsService.friendsNotification());
        {
            return "home/landing-page";
        }
    }

    @RequestMapping
    public class IndexController implements ErrorController {

        @RequestMapping()
        public String error() {
            return "Error handling";
        }

        @Override
        public String getErrorPath() {
            return "/error";
        }
    }

    @RequestMapping(value = "/signedin")
    public String signedIn(Model model, Authentication authentication) {

        CustomUserDetail principal = (authentication != null) ? (CustomUserDetail) authentication.getPrincipal() : null;
        if (principal != null) {
            String role = ((SimpleGrantedAuthority) principal.getAuthorities().iterator().next()).getAuthority();
            switch (role) {
                case ("ROLE_ADMIN"):
                    return "redirect:/";
                case "ROLE_MODERATOR":
                    return "redirect:/";
                case ("ROLE_USER"):
                    return "redirect:/";
            }
        }
        return "/";
    }
}
