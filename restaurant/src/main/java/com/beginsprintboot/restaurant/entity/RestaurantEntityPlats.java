package com.beginsprintboot.restaurant.entity;

import java.io.Serializable;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class RestaurantEntityPlats implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToMany(mappedBy = "plat")
    private Set<RestaurantEntityMenuPlat> menuPlats;

    @Column(name = "description")
    private String description;

    @Column(name = "ingrédients")
    private String ingredients;

    @Column(name = "plat_name", nullable = false)
    private String plat_name;

    @Column(name = "prix", nullable = false)
    private Double prix;

    @Column(name = "image", nullable = false)
    private String image;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }
   
    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String name) {
        this.ingredients = name;
    }
    public String getPlatName() {
        return plat_name;
    }

    public void setPlatName(String name) {
        this.plat_name = name;
    }
    
    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        if (prix == null || prix.toString().trim().isEmpty()) {
            throw new IllegalArgumentException("Prix cannot be null or empty");
        }
        this.prix = prix;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public Set<RestaurantEntityMenuPlat> getMenuPlats() {
        return menuPlats;
    }

    public void setMenuPlats(Set<RestaurantEntityMenuPlat> menuPlats) {
        this.menuPlats = menuPlats;
    }

}
