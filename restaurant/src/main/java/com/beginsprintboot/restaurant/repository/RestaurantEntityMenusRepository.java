package com.beginsprintboot.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beginsprintboot.restaurant.entity.RestaurantEntityMenus;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantEntityMenusRepository extends JpaRepository<RestaurantEntityMenus, Integer> {
    //Trouver les menus correspond à l'id de la carte
    List<RestaurantEntityMenus> findByCarteId(Integer carteId);


    Optional<RestaurantEntityMenus> findById(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM RestaurantEntityMenus r WHERE r.id = :id")
    void deleteMenuInCarte(@Param("id") Integer id);
    
        @Transactional
        @Modifying
        @Query("UPDATE RestaurantEntityMenus m SET m.name = :restaurant_menu, m.description = :restaurant_description WHERE m.id = :id")
        void updateMenuInCarte(@Param("id") Integer id, @Param("restaurant_menu") String restaurant_name, @Param("restaurant_description") String restaurant_description);
    }
