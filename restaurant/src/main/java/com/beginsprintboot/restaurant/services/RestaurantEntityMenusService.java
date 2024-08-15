package com.beginsprintboot.restaurant.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.beginsprintboot.restaurant.entity.RestaurantEntityCartes;
import com.beginsprintboot.restaurant.entity.RestaurantEntityMenuPlat;
import com.beginsprintboot.restaurant.entity.RestaurantEntityMenus;
import com.beginsprintboot.restaurant.repository.RestaurantEntityMenusRepository;
import com.beginsprintboot.restaurant.repository.RestaurantEntityMenuPlatRepository;

@Service
public class RestaurantEntityMenusService {
    
    @Autowired
    RestaurantEntityMenusRepository menusRepository;

    @Autowired
    RestaurantEntityMenuPlatRepository menusPlatsRepository;

    public List<RestaurantEntityMenus> getMenusByCarteId(Integer carteId) {
        return menusRepository.findByCarteId(carteId);
    }
    public RestaurantEntityMenus createMenuForCarte(RestaurantEntityCartes carteId, @RequestParam Map<String, String> allParams){
        RestaurantEntityMenus restaurant = new RestaurantEntityMenus();

        restaurant.setCarte(carteId);
        restaurant.setName(allParams.get("name"));
        restaurant.setDescription(allParams.get("description"));
        menusRepository.save(restaurant);

        return restaurant;
    }
    public void deleteMenuForCarte(Integer id){
        menusRepository.deleteMenuInCarte(id);
    }
    public RestaurantEntityMenus updateMenuForCarte(Integer id){
        return menusRepository.findById(id).get();
    }
    public void updatedMenu(
        @PathVariable("id") Integer id,
        @RequestParam("restaurant_menu") String restaurant_menu,
        @RequestParam("restaurant_description") String restaurant_description
        ){
            menusRepository.updateMenuInCarte(id, restaurant_menu, restaurant_description);
    }
}
