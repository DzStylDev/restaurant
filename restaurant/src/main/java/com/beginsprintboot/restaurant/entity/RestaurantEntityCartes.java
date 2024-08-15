package com.beginsprintboot.restaurant.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class RestaurantEntityCartes {
    private enum Sodas_eau {
        Eau_plate,
        Jus_de_pomme,
        Cocas_colas,
        Ice_tea,
    }
    private enum Boissons_chaude  {
        Café,
        expresso,
        Café_renversé,
        chocolat_chaud,
        Cappucino,
        Café_macchiacto,
        frappé,
        thé,
        infusion, 
        Cocas_colas,
        Ice_tea,
    }
    private enum Salades  {
        Salade_du_marché,
        Salade_gourmande_au_poulet,
        Terrine_campagnarde_à_lail_des_ours_maison,
        Tartare_de_boeuf_maison,
    }
    private enum Plats  {
        Omelette_à_la_piperade_provençale,
        Entrecôte_de_boeuf_suisse_220gr_et_sa_sauce_au_gorgonzola,
        Burger_espagnol,
        Filet_de_rascasse_et_sa_sauce_safranée,
        Tortellinis_parmesan_et_basilic_et_leur_crème_au_pesto,
        Assiette_de_frites_maison
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_restaurant_entity_cartes_restaurant"))
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "carte", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RestaurantEntityMenus> menus = new HashSet<>();


    @Column(name = "soda_eau")
    private String soda_eau;

    @Column(name = "boisson_chaude")
    private String boisson_chaude;

    @Column(name = "salades/entrées")
    private String salades;

    @Column(name = "plats")
    private String plats;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSodaEau() {
        return soda_eau;
    }

    public void setSodas_eau(String name) {
        this.soda_eau = name;
    }
    public String getBoissonChaude() {
        return boisson_chaude;
    }

    public void setBoissonChaude(String name) {
        this.boisson_chaude = name;
    }
    public String getSalades() {
        return salades;
    }

    public void setSalades(String name) {
        this.salades = name;
    }
    
    public String getPlats() {
        return plats;
    }

    public void setPlats(String name) {
        this.plats = name;
    }
    
    public RestaurantEntity getRestaurantId() {
        return restaurant;
    }

    public void setRestaurantId(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }
    

}
