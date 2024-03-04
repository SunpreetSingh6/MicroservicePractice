package com.RatingService.Service;

import com.RatingService.Model.Rating;

import java.util.List;

public interface RatingService {

    Rating addRating(Rating rating);
    List<Rating> getAllRatings();
    List<Rating> getAllRatingsByUserId(String userID);
    List<Rating> getAllRatingsByHotelId(String hotelID);


}
