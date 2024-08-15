package com.beginsprintboot.restaurant.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.beginsprintboot.restaurant.entity.RestaurantEntity;
import com.beginsprintboot.restaurant.entity.RestaurantEntityCartes;
import com.beginsprintboot.restaurant.repository.RestaurantEntityCartesRepository;

@Service
public class RestaurantEntityCarteService {
    
    
    @Autowired
    RestaurantEntityCartesRepository cartesRepository;

    public void addItemToCarte(Integer carteId, @RequestParam Map<String, String> allparams, RestaurantEntityCartes restaurantCarte){
        this.updateCarteCategorieSodaEau(carteId, allparams.get("soda_eau"), restaurantCarte);
        this.updateCarteCategorieBoissonChaude(carteId, allparams.get("boisson_chaude"), restaurantCarte);
        this.updateCarteCategorieSalades(carteId, allparams.get("salades/entrées"), restaurantCarte);
        this.updateCarteCategoriePlats(carteId, allparams.get("plats"), restaurantCarte);

    }
    public void updateCarteCategorieSodaEau(
        Integer id, 
        String soda_eau,
        RestaurantEntityCartes restaurantCartes
    ){
        if (soda_eau != null && !soda_eau.isEmpty()) {
            String itemConcatener = "," + soda_eau;
            cartesRepository.updateCarteSodaEau(id, itemConcatener);
        }
    }
    public void deleteCarteCategorieSodaEau(
        Integer id, 
        List<String> soda_eau,
        RestaurantEntityCartes restaurantCartes
    ){
        List<String> sodaEauList = new ArrayList<>(Arrays.asList(restaurantCartes.getSodaEau().split(",")));

        sodaEauList.removeAll(soda_eau);

        String updatedSodaEau = String.join(",", sodaEauList);
        restaurantCartes.setSodas_eau(updatedSodaEau);

        cartesRepository.save(restaurantCartes);

    }
    
    public void updateCarteCategorieBoissonChaude(
        Integer id, 
        String boisson_chaude,
        RestaurantEntityCartes restaurantCartes
    ){
        if (boisson_chaude != null && !boisson_chaude.isEmpty()) {
            String itemConcatener = "," + boisson_chaude;
            cartesRepository.updateCarteBoissonChaude(id, itemConcatener);
        }
    }
    public void deleteCarteCategorieBoissonChaude(
        Integer id, 
        List<String> boisson_chaude,
        RestaurantEntityCartes restaurantCartes
    ){
        List<String> boissonChaudeList = new ArrayList<>(Arrays.asList(restaurantCartes.getBoissonChaude().split(",")));

        boissonChaudeList.removeAll(boisson_chaude);

        String updatedBoissonChaude = String.join(",", boissonChaudeList);
        restaurantCartes.setBoissonChaude(updatedBoissonChaude);

        cartesRepository.save(restaurantCartes);

    }
    public void updateCarteCategorieSalades(
        Integer id, 
        String salades,
        RestaurantEntityCartes restaurantCartes
    ){
        if (salades != null && !salades.isEmpty()) {
            String itemConcatener = "," + salades;
            cartesRepository.updateCarteSalades(id, itemConcatener);
        }
    }
    public void deleteCarteCategorieSalades(
        Integer id, 
        List<String> salades,
        RestaurantEntityCartes restaurantCartes
    ){
        List<String> saladesList = new ArrayList<>(Arrays.asList(restaurantCartes.getSalades().split(",")));

        saladesList.removeAll(salades);

        String updatedSalades = String.join(",", saladesList);
        restaurantCartes.setSalades(updatedSalades);

        cartesRepository.save(restaurantCartes);

    }
    public void updateCarteCategoriePlats(
        Integer id, 
        String plats,
        RestaurantEntityCartes restaurantCartes
    ){
        if (plats != null && !plats.isEmpty()) {
            String itemConcatener = "," + plats;
            cartesRepository.updateCartePlats(id, itemConcatener);
        }
    }
    public void deleteCarteCategoriePlats(
        Integer id, 
        List<String> plats,
        RestaurantEntityCartes restaurantCartes
    ){
        List<String> platsList = new ArrayList<>(Arrays.asList(restaurantCartes.getPlats().split(",")));

        platsList.removeAll(plats);

        String updatedPlats = String.join(",", platsList);
        restaurantCartes.setPlats(updatedPlats);

        cartesRepository.save(restaurantCartes);
    
    }
     public RestaurantEntityCartes createRestaurantCarte(RestaurantEntity restaurant_id, @RequestParam Map<String, String> allParams){
        RestaurantEntityCartes restaurantCartes = new RestaurantEntityCartes();
        restaurantCartes.setRestaurantId(restaurant_id);
        restaurantCartes.setSodas_eau("");
        restaurantCartes.setBoissonChaude("");
        restaurantCartes.setSalades("");
        restaurantCartes.setPlats("");
        cartesRepository.save(restaurantCartes);
        return restaurantCartes;
    }

}
