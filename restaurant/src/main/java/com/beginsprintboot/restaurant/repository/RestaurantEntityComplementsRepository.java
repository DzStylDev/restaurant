package com.beginsprintboot.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beginsprintboot.restaurant.entity.RestaurantEntityComplements;

import jakarta.transaction.Transactional;

@Repository
public interface RestaurantEntityComplementsRepository extends JpaRepository<RestaurantEntityComplements, Integer> {
   
    @Transactional
    @Modifying
    @Query("UPDATE RestaurantEntityComplements r SET r.button_ajout_menu = :button_ajout_menu WHERE r.id = :id")
    void updateRestaurantButtonAjoutMenu(@Param("id") Integer id, @Param("button_ajout_menu") String button_ajout_menu);
    
    @Transactional
    @Modifying
    @Query("UPDATE RestaurantEntityComplements r SET r.button_ajout_plat = :button_ajout_plat WHERE r.id = :id")
    void updateRestaurantButtonAjoutPlat(@Param("id") Integer id, @Param("button_ajout_plat") String button_ajout_plat);
    
}
