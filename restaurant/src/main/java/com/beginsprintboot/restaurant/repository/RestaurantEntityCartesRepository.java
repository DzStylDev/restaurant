package com.beginsprintboot.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beginsprintboot.restaurant.entity.RestaurantEntityCartes;

import jakarta.transaction.Transactional;

@Repository
public interface RestaurantEntityCartesRepository extends JpaRepository<RestaurantEntityCartes, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE RestaurantEntityCartes r SET r.soda_eau = CONCAT(r.soda_eau, :soda_eau) WHERE r.id = :id")
    void updateCarteSodaEau(@Param("id") Integer id, @Param("soda_eau") String soda_eau);

    @Transactional
    @Modifying
    @Query("UPDATE RestaurantEntityCartes r SET r.boisson_chaude = CONCAT(r.boisson_chaude, :boisson_chaude) WHERE r.id = :id")
    void updateCarteBoissonChaude(@Param("id") Integer id, @Param("boisson_chaude") String boisson_chaude);

    @Transactional
    @Modifying
    @Query("UPDATE RestaurantEntityCartes r SET r.salades = CONCAT(r.salades, :salades) WHERE r.id = :id")
    void updateCarteSalades(@Param("id") Integer id, @Param("salades") String salades);
    
    @Transactional
    @Modifying
    @Query("UPDATE RestaurantEntityCartes r SET r.plats = CONCAT(r.plats, :plats) WHERE r.id = :id")
    void updateCartePlats(@Param("id") Integer id, @Param("plats") String plats);
}