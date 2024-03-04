package com.UserService.ServiceImpl;

import com.UserService.Exception.ResourseNotFoundException;
import com.UserService.ExternalService.HotelService;
import com.UserService.Model.Hotel;
import com.UserService.Model.Rating;
import com.UserService.Model.User;
import com.UserService.Repository.UserRepository;
import com.UserService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {


         List<User> allUsers = userRepository.findAll().stream().map(u -> {

//             ArrayList ratingByUserId = restTemplate.getForObject("http://localhost:8084/ratings/users/" + u.getUserId(), ArrayList.class);
//             u.setRatings(ratingByUserId);

             Rating[] ratingByUserId = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + u.getUserId(), Rating[].class);
             List<Rating> ratings = Arrays.stream(ratingByUserId).collect(Collectors.toList());
             List<Rating> ratingList = ratings.stream().map( r -> {
                 ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + r.getHotelId(), Hotel.class);
                 Hotel hotel = forEntity.getBody();
                 r.setHotel(hotel);
                 return r;
             }).collect(Collectors.toList());

             u.setRatings(ratingList);
             return u;
         }).collect(Collectors.toList());
        System.out.println("allUsers = " + allUsers);
         
        return allUsers;
    }

    @Override
    public User getUser(String userId) {

/*
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourseNotFoundException("User not found with id :- " + userId));

//      Fetching ratings of a user by userId from RatingService
//      Calling rating service using RestTemplate;

//        We are calling the service by using hardcoded host and port which is not a good apporoach
//        Instead we should always use service name
//        Rating[] ratingByUserId = restTemplate.getForObject("http://localhost:8084/ratings/users/" + user.getUserId(), Rating[].class);

//        To use service name instead of hardcoded host and port we need to enable it on restTemplate
//        To enable it we need to use @LoadBalanced annotation on restTemplate
//        @LoadBalanced annotation enables us to call the service by name instead of host and port
//        Because by using @LoadBalanced it will distribute the load/traffic to multiple ways by using service name
        Rating[] ratingByUserId = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

//        System.out.println("ratingByUserId = " + ratingByUserId);

        List<Rating> ratings = Arrays.stream(ratingByUserId).collect(Collectors.toList());
//        System.out.println("ratings = " + ratings);

          List<Rating> ratingList = ratings.stream().map(r -> {

//            By using getForObject()
//            Hotel hotelDetails = restTemplate.getForObject("http://localhost:8084/ratings/hotels/"+r.getHotelId(), Hotel.class);
//            System.out.println("hotelDetails = " + hotelDetails);

//            By using getForEntity()
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + r.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();

            r.setHotel(hotel);
            return r;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;

*/

        /*
        NOW WE WILL USE FEIGN CLIENT TO CALL THE SERVICES
        Feing Client -  1. Feign client is a declarative HTTP web client developed by NETFLIX
                        2. If we want to use feign client , create an interface and annotate it.

        To implement it we just need to add the "openfeign" dependency in pom.xml
        After that we need to enable it on ApplicationClass by using @EnableFeignClient
        Now we need to create a interface(Hotel) for the service which we want to call and annotate it with @FeignClient
        Provide name of the service which this(Hotel) service is calling using "name" in @FeignClient
        i.e @FeignClient(name = "HOTEL-SERVICE")

        Now when getHotel() method gets called it's implementation will be provided by the springboot at runtime and used
        hence we call it declarative approach.


        * */

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourseNotFoundException("User not found with id :- " + userId));

        Rating[] ratingByUserId = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingByUserId).collect(Collectors.toList());

        List<Rating> ratingList = ratings.stream().map(r -> {

//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + r.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();

            Hotel hotel = hotelService.getHotel(r.getHotelId());

            r.setHotel(hotel);
            return r;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;




    }
}
