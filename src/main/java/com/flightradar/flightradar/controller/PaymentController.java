package com.flightradar.flightradar.controller;

import com.flightradar.flightradar.model.payment.Payment;
import com.flightradar.flightradar.model.trip.FinalTrip;
import com.flightradar.flightradar.repository.FinalTripRepository;
import com.flightradar.flightradar.security.AllowedForUsers;
import com.flightradar.flightradar.util.ReservationAlreadyPaidException;
import com.flightradar.flightradar.util.ReservationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static com.flightradar.flightradar.service.Static.CurrentUser;

@Controller

public class PaymentController {

    @Autowired
    private FinalTripRepository finalTripRepository;


    @RequestMapping(value = "/check-out", method = RequestMethod.GET)
    @AllowedForUsers
    public ModelAndView checkOut(Model model) {

        FinalTrip finalTrip = finalTripRepository.findByUser_Id(CurrentUser().getUser().getId());
        String reservationFromDB = finalTrip.getReservation().getReservationNumber();

        if (reservationFromDB == null) {

            throw new ReservationNotFoundException();
        }

        boolean isPaid = finalTrip.getReservation().isPaid();

        if (isPaid) {

            throw new ReservationAlreadyPaidException();


        }


        model.addAttribute("reservationNumber", reservationFromDB);
        return new ModelAndView("check-out/payment", "Payment", new Payment());

    }


    @RequestMapping(value = "/check-out/pay/{reservationNumber}", method = RequestMethod.POST)
    public String pay(@PathVariable("reservationNumber") String reservationNumber, @ModelAttribute("Payment") Payment Payment) {


        FinalTrip finalTrip = finalTripRepository.findByUser_Id(CurrentUser().getUser().getId());
        String reservationFromDB = finalTrip.getReservation().getReservationNumber();

        if (reservationFromDB == null || !reservationNumber.equals(reservationFromDB)) {

            throw new ReservationNotFoundException();
        }

        boolean isPaid = finalTrip.getReservation().isPaid();

        if (isPaid) {

            throw new ReservationAlreadyPaidException();


        }

        finalTrip.getReservation().setPaid(true);
        finalTripRepository.save(finalTrip);

        return "redirect:/";
    }

}
