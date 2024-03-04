package com.HotelService.Service;

import com.HotelService.Model.Hotel;

import java.util.List;

public interface HotelService {

    Hotel addHotel(Hotel hotel);
    Hotel getHotel(String hotelId);
    List<Hotel> getHotels();

}
