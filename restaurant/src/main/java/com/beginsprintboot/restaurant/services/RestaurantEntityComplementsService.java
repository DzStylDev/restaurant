package com.beginsprintboot.restaurant.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.beginsprintboot.restaurant.entity.RestaurantEntity;
import com.beginsprintboot.restaurant.entity.RestaurantEntityComplements;
import com.beginsprintboot.restaurant.repository.RestaurantEntityComplementsRepository;

@Service
public class RestaurantEntityComplementsService {
    
    @Autowired
    RestaurantEntityComplementsRepository repository;

     public List<RestaurantEntityComplements> getAllComplements(){
        return repository.findAll();
    }
    public void updateRestaurantComplementInformation(Integer id, @RequestParam Map<String, String> allparams, RestaurantEntityComplements restaurantComplements){
        this.updateRestaurantButtonAjoutMenu(id, allparams.get("button_ajout_menu"), restaurantComplements);
        this.updateRestaurantButtonAjoutPlat(id, allparams.get("button_ajout_plat"), restaurantComplements);
    }
       public void updateRestaurantButtonAjoutMenu(
        Integer id, 
        String button_ajout_menu,
        RestaurantEntityComplements restaurantComplements
    ){
        if (button_ajout_menu != null && !button_ajout_menu.isEmpty()) {
            repository.updateRestaurantButtonAjoutMenu(id, button_ajout_menu);
        } else {
            repository.updateRestaurantButtonAjoutMenu(id, restaurantComplements.getButton_ajout_menu());
        }
    }
    public void updateRestaurantButtonAjoutPlat(
        Integer id, 
        String button_ajout_plat,
        RestaurantEntityComplements restaurantComplements
    ){
        if (button_ajout_plat != null && !button_ajout_plat.isEmpty()) {
            repository.updateRestaurantButtonAjoutPlat(id, button_ajout_plat);
        } else {
            repository.updateRestaurantButtonAjoutPlat(id, restaurantComplements.getButton_ajout_plat());
        }
    }
    public RestaurantEntityComplements createRestaurantComplement(RestaurantEntity restaurant_id, @RequestParam Map<String, String> allParams){
        RestaurantEntityComplements restaurantComplements = new RestaurantEntityComplements();
        restaurantComplements.setRestaurantId(restaurant_id);
        restaurantComplements.setButton_ajout_menu(allParams.get("button_ajout_menu"));
        restaurantComplements.setButton_ajout_plat(allParams.get("button_ajout_plat"));
        restaurantComplements.setLink1(allParams.get("link1"));
        restaurantComplements.setLink2(allParams.get("link2"));
        repository.save(restaurantComplements);
        return restaurantComplements;
    }
    
}
