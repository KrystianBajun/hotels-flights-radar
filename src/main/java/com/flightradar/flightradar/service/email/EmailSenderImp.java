package com.flightradar.flightradar.service.email;

import com.flightradar.flightradar.model.email.Email;
import com.flightradar.flightradar.model.trip.flight.Flight;
import com.flightradar.flightradar.model.user.User;
import com.flightradar.flightradar.model.trip.FinalTrip;
import com.flightradar.flightradar.repository.FlightRepository;
import com.flightradar.flightradar.repository.ReservationRepository;
import com.flightradar.flightradar.repository.FinalTripRepository;
import com.flightradar.flightradar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.flightradar.flightradar.service.Static.ActualDate;

@Service
@Component
public class EmailSenderImp implements EmailSender {

    @Autowired
    private
    UserRepository users;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    private
    FlightRepository flightRepository;

    @Autowired
    FinalTrip finalTrip;

    @Autowired
    private
    FinalTripRepository finalTripRepository;

    @Autowired
    private
    TemplateEngine templateEngine;

    @Override
    public void sendEmail(String to, String title, String message) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo("krystian.bajun@gmail.com");
            helper.setFrom("cheapflightsearcherapp@gmail.com");
            helper.setSubject(title);
            helper.setText(message, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);
    }

    @Override
    public void sendNewsletter(String title, String message) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        Iterable<User> us = users.findAll();

        try {
            for (User user : us) {
                String emailAddress = user.getEmail();
                System.out.println(emailAddress);
                MimeMessageHelper helper = new MimeMessageHelper(mail, true);
                helper.setTo(emailAddress);
                helper.setFrom("cheapflightsearcherapp@gmail.com");
                helper.setSubject(title);
                helper.setText(message, true);
                helper.setReplyTo(emailAddress);
                Thread.sleep(30000);
                javaMailSender.send(mail);
            }
        } catch (MessagingException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void isTripFinishedAndPaid() throws ParseException {
        List<Flight> tripFinishedTday = flightRepository.findByReturnTime(ActualDate());
        List<FinalTrip> tripFinishedTdayAndPaid = new ArrayList<>();
        for (Flight tripFinished : tripFinishedTday) {
            if (finalTripRepository.findByflightId(tripFinished.getId()).getReservation().isPaid()) {
                tripFinishedTdayAndPaid.add(finalTripRepository.findByflightId(tripFinished.getId()));
            }
        }
        if (tripFinishedTdayAndPaid.size() >= 1) {
            feedBackPassData(tripFinishedTdayAndPaid);
        }
    }

    private void feedBackPassData(List<FinalTrip> tripFinishedTdayUsers) {
        for (FinalTrip user : tripFinishedTdayUsers) {
            String email = user.getUser().getEmail();
            Long hotelId = user.getHotel().getId();
            String reservation = user.getReservation().getReservationNumber();
            feedbackDataPrepare(email, hotelId, reservation);
        }
    }

    private void feedbackDataPrepare(String to, long hotelRateId, String reservationNumber) {
        Email feedback = new Email(
                "Dear Customer,",
                "thank You for your trip",
                "please rate your accommodation",
                "~~ Trip Hotel and Flight Searcher"
        );
        Context context = new Context();
        context.setVariable("header", feedback.getHeader());
        context.setVariable("title", feedback.getTitle());
        context.setVariable("message", feedback.getMessage());
        context.setVariable("hotelId", hotelRateId);
        context.setVariable("reservation", reservationNumber);
        String body = templateEngine.process("email/feedback-template", context);
        String subject = "Best Trip Searcher - Rate Hotel";
        sendFeedBackEmail(to, subject, body);
    }

    @Override
    public void sendFeedBackEmail(String to, String subject, String message) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo("krystian.bajun@gmail.com");
            helper.setFrom("cheapflightsearcherapp@gmail.com");
            helper.setSubject(subject);
            helper.setText(message, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("EMAIL HAS BEEN SENT, to :" + to);
        javaMailSender.send(mail);
    }
}
