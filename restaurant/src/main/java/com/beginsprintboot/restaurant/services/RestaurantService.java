package com.beginsprintboot.restaurant.services;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.beginsprintboot.restaurant.entity.RestaurantEntity;
import com.beginsprintboot.restaurant.repository.RestaurantEntityRepository;

@Service
public class RestaurantService {
    
    @Autowired
    RestaurantEntityRepository repository;


    public Optional<RestaurantEntity> restaurantPresent(String name, String password){
        return repository.findByNameAndPassword(name, password);
    }
    public RestaurantEntity getRestaurant(String name, String password){
        return repository.findByNameAndPassword(name, password).get();
    }
    public Optional<RestaurantEntity> getRestaurantByUrl(String url){
        return repository.findRestaurantByUrl(url);
    }
    public List<RestaurantEntity> getAllRestaurant(){
        return repository.findAll();
    }
    public RestaurantEntity createRestaurant(@RequestParam Map<String, String> allParams){
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName(allParams.get("name"));
        restaurant.setPassword(allParams.get("password"));
        restaurant.setUrl(allParams.get("url") + "_restaurant");
        restaurant.setColor(allParams.get("color"));
        restaurant.setImage(allParams.get("image"));
        repository.save(restaurant);

        return restaurant;
    }
    // public void updatePassword(Integer id, String newPassword) {
    //     restaurantRepository.updatePasswordById(id, newPassword);
    // }
    public void updateRestaurantInformation(Integer id, @RequestParam Map<String, String> allparams, Optional<RestaurantEntity> restaurant){
        this.updateRestaurantName(id, allparams.get("restaurant_name"), restaurant.get());
        this.updateRestaurantPassword(id, allparams.get("restaurant_password"), restaurant.get());
        this.updateRestaurantUrl(id, allparams.get("restaurant_url"), restaurant.get());
        this.updateRestaurantImage(id, allparams.get("restaurant_image"), restaurant.get());
        this.updateRestaurantColor(id, allparams.get("restaurant_color"), restaurant.get());
        this.updateRestaurantAddress(id, allparams.get("restaurant_address"), restaurant.get());
        this.updateRestaurantContact(id, allparams.get("restaurant_contact"), restaurant.get());
        this.updateRestaurantTel(id, allparams.get("restaurant_tel"), restaurant.get());
    }
    public void updateRestaurantName(
        Integer id, 
        String name,
        RestaurantEntity restaurant
    ){
        if (name != null && !name.isEmpty()) {
                repository.updateRestaurantName(id, name);
        } else {
                repository.updateRestaurantName(id, restaurant.getName());
        }
    }
    
    public void updateRestaurantPassword(
        Integer id, 
        String password,
        RestaurantEntity restaurant
    ){
        if (password != null && !password.isEmpty()) {
            repository.updateRestaurantPassword(id, password);
        } else {
            repository.updateRestaurantPassword(id, restaurant.getPassword());
        }
    }
    
    public void updateRestaurantUrl(
        Integer id, 
        String url,
        RestaurantEntity restaurant
    ){
        if (url != null && !url.isEmpty()) {
            repository.updateRestaurantUrl(id, url);
        } else {
            repository.updateRestaurantUrl(id, restaurant.getUrl());
        }
    }
    
    public void updateRestaurantImage(
        Integer id, 
        String image,
        RestaurantEntity restaurant
    ){
        if (image != null && !image.isEmpty()) {
            repository.updateRestaurantImage(id, image);
        } else {
            repository.updateRestaurantImage(id, restaurant.getImage());
        }
    }
    
    public void updateRestaurantColor(
        Integer id, 
        String color,
        RestaurantEntity restaurant
    ){
        if (color != null && !color.isEmpty()) {
            repository.updateRestaurantColor(id, color);
        } else {
            repository.updateRestaurantColor(id, restaurant.getColor());
        }
    }
    
    public void updateRestaurantAddress(
        Integer id, 
        String address,
        RestaurantEntity restaurant
    ){
        if (address != null && !address.isEmpty()) {
            repository.updateRestaurantAddress(id, address);
        } else {
            repository.updateRestaurantAddress(id, restaurant.getAddress());
        }
    }

    public void updateRestaurantContact(
        Integer id, 
        String contact,
        RestaurantEntity restaurant
    ){
        if (contact != null && !contact.isEmpty()) {
            repository.updateRestaurantContact(id, contact);
        } else {
            repository.updateRestaurantContact(id, restaurant.getContact());
        }
    }
    public void updateRestaurantTel(
        Integer id, 
        String tel,
        RestaurantEntity restaurant
    ){
        if (tel != null && !tel.isEmpty()) {
            repository.updateRestaurantTel(id, tel);
        } else {
            repository.updateRestaurantTel(id, restaurant.getTel());
        }
    }
 
    
}
