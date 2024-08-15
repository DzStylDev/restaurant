package com.beginsprintboot.restaurant.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beginsprintboot.restaurant.entity.RestaurantEntity;
import com.beginsprintboot.restaurant.entity.RestaurantEntityRating;
import com.beginsprintboot.restaurant.repository.RestaurantEntityRatingRepository;
import com.beginsprintboot.restaurant.repository.RestaurantEntityRepository;

import jakarta.transaction.Transactional;

@Service
public class RestaurantEntityRatingService {
    
    @Autowired
    RestaurantEntityRatingRepository ratingRepository;

    @Autowired
    RestaurantEntityRepository restaurantRepository;

    public List<RestaurantEntityRating> getAllRating(){
            return ratingRepository.findAll();
    }

    @Transactional
    public void addRatingToRestaurant(Integer restaurantId, RestaurantEntityRating rating) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new IllegalArgumentException("Id du restaurant invalide"));

        rating.setRestaurant(restaurant);
        ratingRepository.save(rating);
    }
    
}
