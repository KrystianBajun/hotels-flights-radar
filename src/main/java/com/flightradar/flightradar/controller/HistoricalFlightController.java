package com.flightradar.flightradar.controller;

import com.flightradar.flightradar.model.trip.TripRequest;
import com.flightradar.flightradar.model.historicalFlight.Historical;
import com.flightradar.flightradar.repository.HistoricalFlightRepository;
import com.flightradar.flightradar.security.AllowedForUsers;
import com.flightradar.flightradar.service.connect.ConnectServiceFlight;
import com.flightradar.flightradar.service.flight.HistoricalFlightService;
import com.flightradar.flightradar.util.DatabaseSaveException;
import com.flightradar.flightradar.util.HistoricalNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;

import static com.flightradar.flightradar.service.Static.CurrentUser;


@Controller
@RequestMapping(value = "/historical")
public class HistoricalFlightController {

    @Autowired
    ConnectServiceFlight connectService;

    @Autowired
    private
    HistoricalFlightService service;

    @Autowired
    private
    HistoricalFlightRepository historicalFlightRepository;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @AllowedForUsers
    public ModelAndView historicalForm() {
        return new ModelAndView("historical/historical-forms", "TripRequest", new TripRequest());

    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    @AllowedForUsers
    public String connectAndSave(@Valid @ModelAttribute("TripRequest") TripRequest tripRequest, BindingResult result) {


        String url = service.buildUrl(tripRequest);
        String output = service.connect(url);

        try {
            service.save(output, tripRequest);

        } catch (Exception e) {

            throw new DatabaseSaveException();

        }


        if (result.hasErrors()) {
            return "error";

        } else

            return "redirect:chart";
    }


    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    @AllowedForUsers
    public String chart(@Valid @ModelAttribute("TripRequest") TripRequest tripRequest, ModelMap model) {


        ArrayList<Historical> historicalList = historicalFlightRepository.findByUserName(CurrentUser().getUsername());
        ArrayList<BigDecimal> pricelist = new ArrayList<>();
        for (int i = 0; i < historicalList.size(); i++) {
            pricelist.add(historicalList.get(i).getPrice());

        }
        for (Historical his : historicalList) {
            if (his.getId() == null) {

                throw new HistoricalNotFoundException();

            }
        }

        model.addAttribute("average", service.averagePrice(historicalList));
        model.addAttribute("cheapest1", service.sortList(pricelist)[0]);
        model.addAttribute("cheapest2", service.sortList(pricelist)[1]);
        model.addAttribute("cheapest3", service.sortList(pricelist)[2]);
        model.addAttribute("highest1", service.sortList(pricelist)[pricelist.size() - 1]);
        model.addAttribute("highest2", service.sortList(pricelist)[pricelist.size() - 2]);
        model.addAttribute("highest3", service.sortList(pricelist)[pricelist.size() - 3]);

        historicalFlightRepository.removeByUserName(CurrentUser().getUsername());

        return "historical/chart.html";
    }


}