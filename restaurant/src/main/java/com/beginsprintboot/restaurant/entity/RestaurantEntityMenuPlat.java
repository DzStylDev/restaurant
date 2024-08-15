package com.beginsprintboot.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class RestaurantEntityMenuPlat implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "menu_id", insertable = false, updatable = false)
    private RestaurantEntityMenus menu;

    @ManyToOne
    @JoinColumn(name = "plat_id", insertable = false, updatable = false)
    private RestaurantEntityPlats plat;

    private Integer menu_id;
    private Integer plat_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public RestaurantEntityMenus getMenu() {
        return menu;
    }

    public void setMenu(RestaurantEntityMenus menu) {
        this.menu = menu;
    }
    public RestaurantEntityPlats getPlat() {
        return plat;
    }

    public void setPlat(RestaurantEntityPlats plat) {
        this.plat = plat;
    }
    
    public Integer getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Integer menu_id) {
        this.menu_id = menu_id;
    }

    public Integer getPlat_id() {
        return plat_id;
    }

    public void setPlat_id(Integer plat_id) {
        this.plat_id = plat_id;
    }
}
