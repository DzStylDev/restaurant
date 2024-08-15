package com.beginsprintboot.restaurant.controllers;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.beginsprintboot.restaurant.entity.RestaurantEntity;
import com.beginsprintboot.restaurant.entity.RestaurantEntityCartes;
import com.beginsprintboot.restaurant.entity.RestaurantEntityMenus;
import com.beginsprintboot.restaurant.entity.RestaurantEntityPlats;
import com.beginsprintboot.restaurant.entity.RestaurantEntityRating;
import com.beginsprintboot.restaurant.services.RestaurantEntityCarteService;
import com.beginsprintboot.restaurant.services.RestaurantEntityComplementsService;
import com.beginsprintboot.restaurant.services.RestaurantEntityMenuPlatService;
import com.beginsprintboot.restaurant.services.RestaurantEntityMenusService;
import com.beginsprintboot.restaurant.services.RestaurantEntityPlatService;
import com.beginsprintboot.restaurant.services.RestaurantEntityRatingService;
import com.beginsprintboot.restaurant.services.RestaurantService;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;
    
    @Autowired
    RestaurantEntityComplementsService complementService;

    @Autowired
    RestaurantEntityMenusService menusService;

    @Autowired
    RestaurantEntityPlatService platService;

    @Autowired
    RestaurantEntityMenuPlatService menuPlatService;

    @Autowired
    RestaurantEntityCarteService carteService;

    @Autowired
    RestaurantEntityRatingService ratingService;

    @GetMapping("/verif_restaurant")
    public String restaurant_exist(Model model) {
        return "form_restaurant";
    }
    @GetMapping("/hasPermission/{url}")
    public String hasPermission(Model model, @PathVariable("url") String url) {
        model.addAttribute("url", url);
        return "form_restaurant_perm";
    }

    @GetMapping("/configuration")
    public String create_restaurant_configuration() {
        return "configuration";
    }

    @PostMapping("/get_configuration")
    public String checkRestaurant(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            Model model) {

        Optional<RestaurantEntity> getRestaurant = restaurantService.restaurantPresent(name, password);
        
        if (getRestaurant.isPresent()) {
            model.addAttribute("restaurant", getRestaurant.get());
           
            // if(getRestaurant.get().getRating() != null){

            //     List<RestaurantEntityRating> ratings = Arrays.asList(getRestaurant.get().getRating());
            //     // model.addAttribute("ratings", ratings);
            //     System.out.println(ratings);
            // }

            return "restaurant";
        } else {
            return "redirect:/configuration";
        }
    }
    @PostMapping("/get_configuration_for_restaurant/{url}")
    public String checkIfYouHavePerm(
            @PathVariable("url") String url,
            @RequestParam("password") String password,
            Model model) {
            
            Optional<RestaurantEntity> getRestaurant = restaurantService.getRestaurantByUrl(url);

            if (getRestaurant.isPresent() && password.equals(getRestaurant.get().getPassword())) {
                model.addAttribute("restaurant", getRestaurant.get());
                return "redirect:/configuration/{url}";
            } else {
                return "redirect:/configuration";
            }
    }

    @GetMapping("configuration/{url}")
    public String myRestaurantConfiguration(@PathVariable("url") String url, Model model) {
        Optional<RestaurantEntity> getRestaurant = restaurantService.getRestaurantByUrl(url);

        if (getRestaurant.isPresent()) {
            
            model.addAttribute("restaurant", getRestaurant.get());
            if (getRestaurant.get().getCarte() != null) {
                
                int carteId = getRestaurant.get().getCarte().getId();   
                List<RestaurantEntityMenus> menus = menusService.getMenusByCarteId(carteId);
                List<RestaurantEntityPlats> plats = platService.getAllPlats();
                
                List<String> getAllSodaEau = Arrays.asList(getRestaurant.get().getCarte().getSodaEau().split(","));
                List<String> getBoissonChaude = Arrays.asList(getRestaurant.get().getCarte().getBoissonChaude().split(","));
                List<String> getSalades = Arrays.asList(getRestaurant.get().getCarte().getSalades().split(","));
                List<String> getPlats = Arrays.asList(getRestaurant.get().getCarte().getPlats().split(","));

                List<Set<RestaurantEntityRating>> ratings = Arrays.asList(getRestaurant.get().getRatings());

                model.addAttribute("getPlats", getPlats);
                model.addAttribute("getAllSodaEau", getAllSodaEau);
                model.addAttribute("getBoissonChaude", getBoissonChaude);
                model.addAttribute("getSalades", getSalades);
                model.addAttribute("getMenu", menus);
                model.addAttribute("carteId", carteId);
                model.addAttribute("plats", plats);
                model.addAttribute("ratings", ratings);

            }
            
            return "configuration_restaurant";
        } else {
            return "redirect:/configuration";
        }
    }

    @GetMapping("/{url}")
    public String myRestaurant(@PathVariable("url") String url, Model model) {
        Optional<RestaurantEntity> getRestaurant = restaurantService.getRestaurantByUrl(url);

        if (getRestaurant.isPresent()) {
            model.addAttribute("restaurant", getRestaurant.get());

            if (getRestaurant.get().getCarte() != null) {
                
                int carteId = getRestaurant.get().getCarte().getId();   
                List<RestaurantEntityMenus> menus = menusService.getMenusByCarteId(carteId);
                
                List<String> getAllSodaEau = Arrays.asList(getRestaurant.get().getCarte().getSodaEau().split(","));
                List<String> getBoissonChaude = Arrays.asList(getRestaurant.get().getCarte().getBoissonChaude().split(","));
                List<String> getSalades = Arrays.asList(getRestaurant.get().getCarte().getSalades().split(","));
                List<String> getPlats = Arrays.asList(getRestaurant.get().getCarte().getPlats().split(","));

                model.addAttribute("getAllSodaEau", getAllSodaEau);
                model.addAttribute("getBoissonChaude", getBoissonChaude);
                model.addAttribute("getSalades", getSalades);
                model.addAttribute("getPlats", getPlats);
                model.addAttribute("getMenu", menus);
            }
            return "restaurant";
        } else {
            return "redirect:/configuration";
        }
    }

    @Value("${upload.directory}")
    private String uploadDirectory;

    @PostMapping("/configuration/create")
    public String create_restaurant(
            @RequestParam Map<String, String> allParams,
            @RequestParam Map<String, String> allParamsComplement,
            @RequestParam Map<String, String> allParamsCarte,
            // @RequestParam("image") MultipartFile image,
            // RedirectAttributes redirectAttributes,
            Model model) {
        String name = allParams.get("name");
        String password = allParams.get("password");
        String url = allParams.get("url");
        String couleur = allParams.get("color");
        String image = allParams.get("image");
        String button_ajout_menu = allParamsComplement.get("button_ajout_menu");
        String button_ajout_plat = allParamsComplement.get("button_ajout_plat");
        String link1 = allParamsComplement.get("link2");
        String link2 = allParamsComplement.get("link2");

        if (name != null && !name.isEmpty() &&
                password != null && !password.isEmpty() &&
                url != null && !url.isEmpty() &&
                couleur != null && !couleur.isEmpty() &&
                image != null && !image.isEmpty() &&
                button_ajout_menu != null && !button_ajout_menu.isEmpty() &&
                button_ajout_plat != null && !button_ajout_plat.isEmpty() &&
                link1 != null && !link1.isEmpty() &&
                link2 != null && !link2.isEmpty()) {

            if (!image.isEmpty()) {
                // String originalFilename = image.getOriginalFilename();

                // if (originalFilename != null) {
                //     String filename = StringUtils.cleanPath(originalFilename);

                //     try {
                //         // Définir le répertoire de téléchargement
                //         // Path uploadPath = Paths.get(uploadDirectory);
        
                //         // Vérifier si le répertoire existe, sinon le créer
                //         // if (!Files.exists(uploadPath)) {
                //         //     Files.createDirectories(uploadPath);
                //         // }
                //         // Définir l'emplacement cible
                //         // Path targetLocation = uploadPath.resolve(filename);
        
                //         // Copier le fichier vers l'emplacement cible
                //         // Files.copy(image.getInputStream(), targetLocation);
                        
                //         // String encodedImageName = UriUtils.encodePath(filename, StandardCharsets.UTF_8);

                        
                //     } catch (IOException e) {
                //         e.printStackTrace();
                //     }
                // }
                RestaurantEntity restaurant = restaurantService.createRestaurant(allParams);
                complementService.createRestaurantComplement(restaurant, allParamsComplement);
                carteService.createRestaurantCarte(restaurant, allParamsCarte);
                
            }
            // redirectAttributes.addFlashAttribute("message", "Le restaurant à était créer
            // avec success");

        }
        return "redirect:/verif_restaurant";
    }
    @GetMapping("/restaurants")
    public String showRestaurants(Model model) {
        List<RestaurantEntity> restaurants = restaurantService.getAllRestaurant();
        model.addAttribute("restaurants", restaurants);
        return "restaurants";
    }
    
    @GetMapping("/restaurant_carte/{url}")
    public String carteRestaurant(@PathVariable("url") String url, Model model) {
        Optional<RestaurantEntity> getRestaurant = restaurantService.getRestaurantByUrl(url);

        if (getRestaurant.isPresent()) {
            model.addAttribute("restaurant", getRestaurant.get());

            if (getRestaurant.get().getCarte() != null) {
                
                int carteId = getRestaurant.get().getCarte().getId();   
                List<RestaurantEntityMenus> menus = menusService.getMenusByCarteId(carteId);
                
                List<String> getAllSodaEau = Arrays.asList(getRestaurant.get().getCarte().getSodaEau().split(","));
                List<String> getBoissonChaude = Arrays.asList(getRestaurant.get().getCarte().getBoissonChaude().split(","));
                List<String> getSalades = Arrays.asList(getRestaurant.get().getCarte().getSalades().split(","));
                List<String> getPlats = Arrays.asList(getRestaurant.get().getCarte().getPlats().split(","));

                model.addAttribute("getAllSodaEau", getAllSodaEau);
                model.addAttribute("getBoissonChaude", getBoissonChaude);
                model.addAttribute("getSalades", getSalades);
                model.addAttribute("getPlats", getPlats);
                model.addAttribute("getMenu", menus);
            }
            return "restaurant_carte";
        } else {
            return "redirect:/configuration";
        }
    }
    @GetMapping("/restaurant_menus/{url}")
    public String MenusRestaurant(@PathVariable("url") String url, Model model) {
        Optional<RestaurantEntity> getRestaurant = restaurantService.getRestaurantByUrl(url);

        if (getRestaurant.isPresent()) {
            model.addAttribute("restaurant", getRestaurant.get());

            if (getRestaurant.get().getCarte() != null) {
                
                int carteId = getRestaurant.get().getCarte().getId();   
                List<RestaurantEntityMenus> menus = menusService.getMenusByCarteId(carteId);
            
                model.addAttribute("getMenu", menus);
            }
            return "restaurant_menus";
        } else {
            return "redirect:/configuration";
        }
    }
    @PostMapping("/save_configuration_restaurant/{url}")
    public String save_configuration_restaurant(Model model,  @RequestParam Map<String, String> allParams, @PathVariable("url") String url) {
        Optional<RestaurantEntity> getRestaurant = restaurantService.getRestaurantByUrl(url);
        
        restaurantService.updateRestaurantInformation(getRestaurant.get().getId(), allParams, getRestaurant);
        complementService.updateRestaurantComplementInformation(getRestaurant.get().getComplement().getId(), allParams, getRestaurant.get().getComplement());

        return "redirect:/configuration/{url}";
    }
    @PostMapping("/configuration/{url}/addItem/{carteId}")
    public String add_item_in_categorie_carte(
        Model model, 
        @RequestParam Map<String, String> allParams,
        @PathVariable("carteId") Integer carteId,
        @PathVariable("url") String url
        ) {
        
        Optional<RestaurantEntity> getRestaurant = restaurantService.getRestaurantByUrl(url);

        RestaurantEntityCartes restaurantCarte = getRestaurant.get().getCarte();

        carteService.addItemToCarte(carteId, allParams, restaurantCarte);
    
        return "redirect:/configuration/{url}";
    }    
    @PostMapping("/configuration/{url}/deleteItem/{carteId}")
    public String delete_item_in_categorie_carte(
        Model model, 
        @RequestParam(value = "soda_eau", required = false) List<String> sodaEauSelected,
        @RequestParam(value = "boisson_chaude", required = false) List<String> boisson_chaudeSelected,
        @RequestParam(value = "salades/entrées", required = false) List<String> salades_entréesSelected,
        @RequestParam(value = "plats", required = false) List<String> platsSelected,
        @PathVariable("url") String url,
        @PathVariable("carteId") Integer carteId
        ) {

        Optional<RestaurantEntity> getRestaurant = restaurantService.getRestaurantByUrl(url);

        RestaurantEntityCartes restaurantCarte = getRestaurant.get().getCarte();
        
        if(sodaEauSelected != null){
            carteService.deleteCarteCategorieSodaEau(carteId, sodaEauSelected, restaurantCarte);
        }
        if(boisson_chaudeSelected != null){
            carteService.deleteCarteCategorieBoissonChaude(carteId, boisson_chaudeSelected, restaurantCarte);
        }
        if(salades_entréesSelected != null){
            carteService.deleteCarteCategorieSalades(carteId, salades_entréesSelected, restaurantCarte);
        }
        if(platsSelected != null){
            carteService.deleteCarteCategoriePlats(carteId, platsSelected, restaurantCarte);
        }
    
        return "redirect:/configuration/{url}";
    }    
    @PostMapping("/configuration/{url}/addMenu/{carteId}")
    public String addMenuInCarte(
        @PathVariable("url") String url,
        @PathVariable("carteId") RestaurantEntityCartes carteId,
        @RequestParam Map<String, String> allParams
    ) {
        menusService.createMenuForCarte(carteId, allParams);
        return "redirect:/configuration/{url}";
    }
    @PostMapping("/configuration/{url}/deleteMenu/{id}")
    public String deleteMenuInCarte(
        @PathVariable("url") String url,
        @PathVariable("id") Integer id,
        @RequestParam Map<String, String> allParams
    ) {
        menusService.deleteMenuForCarte(id);
        return "redirect:/configuration/{url}";
    }
    @PostMapping("/configuration/{url}/updateMenu/{id}")
    public String updateMenuFormInCarte(
        @PathVariable("url") String url,
        @PathVariable("id") Integer id,
        Model model
    ) {
        Optional<RestaurantEntity> getRestaurant = restaurantService.getRestaurantByUrl(url);

        // if (getRestaurant.isPresent()) {
        //     model.addAttribute("restaurant", getRestaurant.get());

            if (getRestaurant.get().getCarte() != null) {
                
                int carteId = getRestaurant.get().getCarte().getId();   

                model.addAttribute("restaurant", getRestaurant.get());
                
                RestaurantEntityMenus getMenu = menusService.updateMenuForCarte(id);

                model.addAttribute("getMenu", getMenu);
            }
        // }
        return "form_update_menu";
    }
    @PostMapping("{url}/modified_menu/{id}")
    public String updatedMenu(
            @PathVariable("id") Integer id,
            @RequestParam("restaurant_menu") String restaurant_menu,
            @RequestParam("restaurant_desc") String restaurant_desc,
            @PathVariable("url") String url
        ) {
        menusService.updatedMenu(id, restaurant_menu, restaurant_desc);
        return "redirect:/configuration/{url}";
    }
    
    
    @PostMapping("/configuration/{url}/addPlatToMenu/{menu_id}")
    public String addPlatToMenu(
            @PathVariable("url") String url,
            @PathVariable("menu_id") Integer menu_id,
            @RequestParam(value = "plats", required = false) List<Integer> platSelected
        ) {
        menuPlatService.associedPlatToMenu(url, menu_id, platSelected);
        return "redirect:/configuration/{url}";
    }
    @PostMapping("/configuration/{url}/deletePlatToMenu/{menu_id}")
    public String deletePlatToMenu(
            @PathVariable("url") String url,
            @RequestParam("plat_id") Integer plat_id,
            @PathVariable("menu_id") Integer menu_id
        ) {
        menuPlatService.dessociedPlatToMenu(menu_id, plat_id);
        return "redirect:/configuration/{url}";
    }
    @PostMapping("/{url}/addComment")
    public String addCommentToRestaurant(
            Model model, @PathVariable("url") String url, 
            @RequestParam Map<String, String> allParams,
            @RequestParam("date") String date
        ) {
        RestaurantEntityRating ratings = new RestaurantEntityRating();
        Optional<RestaurantEntity> getRestaurant = restaurantService.getRestaurantByUrl(url);

        ratings.setName(allParams.get("name"));
        ratings.setEmail(allParams.get("email"));
        ratings.setDate(date);
        ratings.setCommentaire(allParams.get("commentaire"));
        ratings.setNote(allParams.get("note"));
        ratingService.addRatingToRestaurant(getRestaurant.get().getId(), ratings);

        return "redirect:/configuration/{url}";
        
    }
    
    
    
    
    
}
