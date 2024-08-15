package com.beginsprintboot.restaurant.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beginsprintboot.restaurant.entity.RestaurantEntityPlats;
import com.beginsprintboot.restaurant.repository.RestaurantEntityPlatsRepository;

@Service
public class RestaurantEntityPlatService {
 
    @Autowired
    RestaurantEntityPlatsRepository platsRepository;

    // public List<RestaurantEntityPlats> getPlatsByMenuId(Integer menu_id) {
    //     if (menu_id == null) {
    //         throw new IllegalArgumentException("L'id du menu ne peut pas être null");
    //     }
    //     return platsRepository.findByMenus_Id(menu_id);
            
    // }
    public List<RestaurantEntityPlats> getAllPlats(){
        return platsRepository.findAll();
    }
}