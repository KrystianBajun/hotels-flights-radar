package com.flightradar.flightradar.controller;


import com.flightradar.flightradar.model.trip.FinalTrip;
import com.flightradar.flightradar.model.trip.flight.Flight;
import com.flightradar.flightradar.model.trip.hotel.Hotel;
import com.flightradar.flightradar.model.user.User;
import com.flightradar.flightradar.repository.FinalTripRepository;
import com.flightradar.flightradar.repository.UserRepository;
import com.flightradar.flightradar.security.AllowedForUsers;
import com.flightradar.flightradar.service.pdf.PDFService;
import com.flightradar.flightradar.util.FinalTripNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import static com.flightradar.flightradar.service.Static.CurrentUser;

@Controller
@RequestMapping(value = "/PDF")
public class PDFController {
    @Autowired
    private
    PDFService pdfService;
    @Autowired
    private
    FinalTripRepository finalTripRepository;
    @Autowired
    private
    UserRepository userRepository;

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    @AllowedForUsers
    public String generatePDF() throws Exception {

        FinalTrip finalTrip = finalTripRepository.findByUser_Id(CurrentUser().getUser().getId());

        if (finalTrip == null) {
            throw new FinalTripNotFoundException();
        }

        User user = userRepository.findById(finalTrip.getId());
        if (user == null) {
            throw new FinalTripNotFoundException();
        }

        Flight flight = finalTrip.getFlight();
        Hotel hotel = finalTrip.getHotel();

        String reservationNumber = finalTrip.getReservation().getReservationNumber();
        BigDecimal totalPrice = finalTrip.getTotalPrice();
        try {
            pdfService.generateBarCode(reservationNumber);
        } catch (Exception e) {
            System.out.println("Error during generating a bar code" + e);
        }
        Context attributes = pdfService.addAtributes(reservationNumber, totalPrice, flight, hotel);
        pdfService.generatePdf(attributes, reservationNumber);

        return "redirect:" + reservationNumber + "/download";
    }

    @AllowedForUsers
    @RequestMapping("{reservation}/download")
    public ResponseEntity<InputStreamResource> downloadPDF(@PathVariable("reservation") String reservation) throws IOException {
        String path = "C:\\Users\\Krystian\\Desktop\\Flight Radar\\flight-radar\\src\\main\\resources\\pdf\\Reservation-" + reservation + ".pdf";
        File file = new File(path);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
                .body(resource);
    }
}






