package com.beginsprintboot.restaurant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
@Entity
public class RestaurantEntityComplements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_restaurant_entity_complements_restaurant"))
    private RestaurantEntity restaurant;

    @Column(name = "button_ajout_plat")
    private String button_ajout_plat;

    @Column(name = "button_ajout_menu")
    private String button_ajout_menu;

    @Column(name = "link1")
    private String link1;

    @Column(name = "link2")
    private String link2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getButton_ajout_plat() {
        return button_ajout_plat;
    }

    public void setButton_ajout_plat(String name) {
        this.button_ajout_plat = name;
    }

    public String getButton_ajout_menu() {
        return button_ajout_menu;
    }

    public void setButton_ajout_menu(String name) {
        this.button_ajout_menu = name;
    }

    public String getLink1() {
        return link1;
    }

    public void setLink1(String link1) {
        this.link1 = link1;
    }
    public String getLink2() {
        return link2;
    }

    public void setLink2(String link2) {
        this.link2 = link2;
    }
    public RestaurantEntity getRestaurantId() {
        return restaurant;
    }

    public void setRestaurantId(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }
    
}
