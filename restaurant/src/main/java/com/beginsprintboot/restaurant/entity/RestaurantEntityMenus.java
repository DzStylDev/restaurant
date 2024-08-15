package com.beginsprintboot.restaurant.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class RestaurantEntityMenus  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "carte_id", nullable = false)
    private RestaurantEntityCartes carte;

    @OneToMany(mappedBy = "menu")
    private Set<RestaurantEntityMenuPlat> menuPlats;


    // @ManyToMany(cascade = { CascadeType.ALL })
    // @JoinTable(
    //     name = "restaurant_menu_plat", 
    //     joinColumns = { @JoinColumn(name = "menu_id") }, 
    //     inverseJoinColumns = { @JoinColumn(name = "plat_id") }
    // )
    // private Set<RestaurantEntityPlats> plats = new HashSet<>();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public RestaurantEntityCartes getCarte() {
        return carte;
    }

    public void setCarte(RestaurantEntityCartes carte) {
        this.carte = carte;
    }

    public Set<RestaurantEntityMenuPlat> getMenuPlats() {
        return menuPlats;
    }

    public void setMenuPlats(Set<RestaurantEntityMenuPlat> plats) {
        this.menuPlats = plats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
