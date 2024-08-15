package com.beginsprintboot.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beginsprintboot.restaurant.entity.RestaurantEntityMenuPlat;

import jakarta.transaction.Transactional;


@Repository
public interface RestaurantEntityMenuPlatRepository extends JpaRepository<RestaurantEntityMenuPlat, Integer> {

    Optional<RestaurantEntityMenuPlat> findByMenu_idAndPlat_Id(Integer menu_id, Integer plat_id);            
    
    @Transactional
    @Modifying
    @Query("DELETE FROM RestaurantEntityMenuPlat r WHERE r.id = :id")
    void deletePlatToMenu(@Param("id") Integer id);
}
