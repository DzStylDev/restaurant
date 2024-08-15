package com.beginsprintboot.restaurant.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beginsprintboot.restaurant.entity.RestaurantEntityPlats;


@Repository
public interface RestaurantEntityPlatsRepository extends JpaRepository<RestaurantEntityPlats, Integer> {
    
    //Trouver les plats correspond à l'id du menu
    // List<RestaurantEntityPlats> findByMenus_Id(Integer menu_id);
}
