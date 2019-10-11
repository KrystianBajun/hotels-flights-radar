package com.flightradar.flightradar.service.hotel;

import com.flightradar.flightradar.model.trip.hotel.HotelRating;
import com.flightradar.flightradar.repository.HotelRatingRepository;
import com.flightradar.flightradar.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component

public class HotelRateService {

    @Autowired

    HotelRatingRepository hotelRatingRepository;

    @Autowired
    HotelRepository hotelRepository;

    private Double calculateVotes(Double votesNumber, Double currentAverage) {


        return (votesNumber * currentAverage);

    }


    private Double votesAddRate(int score, Double voteSum) {


        return score + voteSum;
    }


    public Double calculateAverageRate(int votesNumber, Double currentAverage, Double score) {
        final int newVote = 1;

        Double VotesSum = calculateVotes(score, votesAddRate(votesNumber, currentAverage));
        int VotesNumber = votesNumber + newVote;

        return VotesSum / VotesNumber;
    }


    public List<HotelRating> topHotels() {


        List<HotelRating> higestScored = hotelRatingRepository.findAllByOrderByAverageDesc();

        return higestScored;
    }
}
