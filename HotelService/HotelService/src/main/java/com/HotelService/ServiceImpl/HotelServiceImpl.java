package com.HotelService.ServiceImpl;

import com.HotelService.Exception.ResourseNotFoundException;
import com.HotelService.Model.Hotel;
import com.HotelService.Repository.HotelRepository;
import com.HotelService.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Hotel addHotel(Hotel hotel) {
        String newId = UUID.randomUUID().toString();
        hotel.setHotelId(newId);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new ResourseNotFoundException("Resourse not found by given id :- " + hotelId));
    }

    @Override
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }
}
