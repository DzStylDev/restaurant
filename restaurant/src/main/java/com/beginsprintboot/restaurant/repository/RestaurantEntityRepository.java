package com.beginsprintboot.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beginsprintboot.restaurant.entity.RestaurantEntity;

import jakarta.transaction.Transactional;

@Repository
public interface RestaurantEntityRepository extends JpaRepository<RestaurantEntity, Integer> {
    Optional<RestaurantEntity> findByNameAndPassword(String name, String password);
    Optional<RestaurantEntity> findRestaurantByUrl(String url);
    
    @Transactional
    @Modifying
    @Query("UPDATE RestaurantEntity r SET r.name = :name WHERE r.id = :id")
    void updateRestaurantName(@Param("id") Integer id, @Param("name") String name);

    @Transactional
    @Modifying
    @Query("UPDATE RestaurantEntity r SET r.password = :password WHERE r.id = :id")
    void updateRestaurantPassword(@Param("id") Integer id, @Param("password") String password);

    @Transactional
    @Modifying
    @Query("UPDATE RestaurantEntity r SET r.url = :url WHERE r.id = :id")
    void updateRestaurantUrl(@Param("id") Integer id, @Param("url") String url);

    @Transactional
    @Modifying
    @Query("UPDATE RestaurantEntity r SET r.image = :image WHERE r.id = :id")
    void updateRestaurantImage(@Param("id") Integer id, @Param("image") String image);
    
    @Transactional
    @Modifying
    @Query("UPDATE RestaurantEntity r SET r.color = :color WHERE r.id = :id")
    void updateRestaurantColor(@Param("id") Integer id, @Param("color") String color);

    @Transactional
    @Modifying
    @Query("UPDATE RestaurantEntity r SET r.address = :address WHERE r.id = :id")
    void updateRestaurantAddress(@Param("id") Integer id, @Param("address") String address);

    @Transactional
    @Modifying
    @Query("UPDATE RestaurantEntity r SET r.contact = :contact WHERE r.id = :id")
    void updateRestaurantContact(@Param("id") Integer id, @Param("contact") String contact);

    @Transactional
    @Modifying
    @Query("UPDATE RestaurantEntity r SET r.tel = :tel WHERE r.id = :id")
    void updateRestaurantTel(@Param("id") Integer id, @Param("tel") String tel);

   

}
