package com.beginsprintboot.restaurant.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.beginsprintboot.restaurant.entity.RestaurantEntity;
import com.beginsprintboot.restaurant.entity.RestaurantEntityMenuPlat;
import com.beginsprintboot.restaurant.entity.RestaurantEntityMenus;
import com.beginsprintboot.restaurant.entity.RestaurantEntityPlats;
import com.beginsprintboot.restaurant.repository.RestaurantEntityMenuPlatRepository;

@Service
public class RestaurantEntityMenuPlatService {
    
    @Autowired
    private RestaurantEntityMenuPlatRepository menuPlatRepository; 

    @Autowired
    private RestaurantEntityMenusService menusService;
        
    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private RestaurantEntityPlatService platService;
    
    public void associedPlatToMenu(
            @PathVariable("url") String url,
            @PathVariable("menu_id") Integer menu_id,
            @RequestParam(value = "plats", required = false) List<Integer> idPlatSelected
        ){
            RestaurantEntityMenuPlat menuPlat = new RestaurantEntityMenuPlat();
            menuPlat.setMenu_id(menu_id);
            
            idPlatSelected.forEach(plat_id -> {
                menuPlat.setPlat_id(plat_id);
            });
            menuPlatRepository.save(menuPlat);

            // Optional<RestaurantEntity> getRestaurant = restaurantService.getRestaurantByUrl(url);
            // int carteId = getRestaurant.get().getCarte().getId(); 
            // List<RestaurantEntityMenus> menus = menusService.getMenusByCarteId(carteId);
            
            // List<RestaurantEntityPlats> plats = platService.getAllPlats();
            // menus.forEach(menu -> menu.getMenuPlats().forEach(plat -> System.out.println("Plat id " + plat.getPlat_id())));
            
            // plats.forEach(plat -> System.out.println(plat.getId()));
                
    }
    public void dessociedPlatToMenu(
            @PathVariable("menu_id") Integer menu_id,
            @RequestParam("plat_id") Integer plat_id
        ){
            // System.out.println("menu_id " + menu_id);
            // System.out.println("plat_id " + plat_id);

            Optional<RestaurantEntityMenuPlat> plat = menuPlatRepository.findByMenu_idAndPlat_Id(menu_id, plat_id);

            menuPlatRepository.deletePlatToMenu(plat.get().getId());
    }
}
