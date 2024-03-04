package com.RatingService.ServiceImpl;

import com.RatingService.Model.Rating;
import com.RatingService.Repository.RatingRepository;
import com.RatingService.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public Rating addRating(Rating rating) {
        String newId = UUID.randomUUID().toString();
        rating.setRatingId(newId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllRatingsByUserId(String userID) {
        return ratingRepository.findByUserId(userID);
    }

    @Override
    public List<Rating> getAllRatingsByHotelId(String hotelID) {
        return ratingRepository.findByHotelId(hotelID);
    }
}
