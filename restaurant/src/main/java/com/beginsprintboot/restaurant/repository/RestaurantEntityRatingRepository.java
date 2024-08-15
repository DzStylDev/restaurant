package com.beginsprintboot.restaurant.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beginsprintboot.restaurant.entity.RestaurantEntityRating;


@Repository
public interface RestaurantEntityRatingRepository extends JpaRepository<RestaurantEntityRating, Integer> {
}
