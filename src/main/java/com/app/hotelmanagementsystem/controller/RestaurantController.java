package com.app.hotelmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.hotelmanagementsystem.entity.Restaurant;
import com.app.hotelmanagementsystem.service.impl.RestaurantServiceImpl;

@Controller
@RequestMapping("/hrms/restaurants")
public class RestaurantController {

private final RestaurantServiceImpl restaurantService;   

@Autowired 
public RestaurantController(RestaurantServiceImpl restaurantService) {
	this.restaurantService = restaurantService;
}

//shows a list of hotels - redirect to listHotelsByPage
@GetMapping("/getallRestaurants")
public String listRestaurants(Model model) {
    return listRestaurantsByPage(model, 1, "name", "asc");
}
//shows a list of hotels paging
@GetMapping("/page/{pageNumber}")
public String listRestaurantsByPage(Model model, @PathVariable("pageNumber") Integer currentPage,
                               @Param("sortField") String sortField, @Param("sortDirection") String sortDirection) {
    Page<Restaurant> page = restaurantService.getAllRestaurantsPage(currentPage, sortField, sortDirection);
    long totalItems = page.getTotalElements();
    int totalPages = page.getTotalPages();

    List<Restaurant> listRestaurants = page.getContent();

    model.addAttribute("currentPage", currentPage);
    model.addAttribute("totalItems", totalItems);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("restaurants",listRestaurants );
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDirection", sortDirection);

    String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
    model.addAttribute("reverseSortDirection", reverseSortDirection);
    return "restaurants";
}
//shows a list of hotels for users - redirect to listHotelsByPageForUsers
@GetMapping("/usr")
public String listRestaurantsForUsers(Model model) {
    return listRestaurantsByPageForUsers(model, 1, "name", "asc");
}
//shows a list of hotels for users paging
@GetMapping("/usr/page/{pageNumber}")
public String listRestaurantsByPageForUsers(Model model, @PathVariable("pageNumber") Integer currentPage,
                               @Param("sortField") String sortField, @Param("sortDirection") String sortDirection) {
    Page<Restaurant> page = restaurantService.getAllRestaurantsPage(currentPage, sortField, sortDirection);
    long totalItems = page.getTotalElements();
    int totalPages = page.getTotalPages();

    List<Restaurant> listRestaurants = page.getContent();

    model.addAttribute("currentPage", currentPage);
    model.addAttribute("totalItems", totalItems);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("hotels", listRestaurants);
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDirection", sortDirection);

    String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
    model.addAttribute("reverseSortDirection", reverseSortDirection);
    return "restaurants_user";
}

//shows rooms within a specific hotel
@GetMapping("/{restaurantId}/tables")
public String listTablesInRestaurant(@PathVariable Long restaurantId, Model model) {
    model.addAttribute("tables", restaurantService.findTablesInRestaurantByRestaurantId(restaurantId));
    return "tables_in_restaurant";
}
//shows rooms within a specific hotel for users
@GetMapping("/usr/{restaurantId}/tables")
public String listTablesInRestaurantForUsers(@PathVariable Long RestaurantId, Model model) {
    model.addAttribute("tables", restaurantService.findTablesInRestaurantByRestaurantId(RestaurantId));
    return "tables_in_restaurant_user";
}

// form for creating a new hotel
@GetMapping("/new")
public String addRestaurantForm(Model model) {
    Restaurant restaurant = new Restaurant();
    model.addAttribute("restaurant", restaurant);
    return "restaurant_create";
}
// creates a new hotel
@PostMapping
public String addNewRestaurant(@ModelAttribute("restaurant") Restaurant restaurant) {
    restaurantService.addNewRestaurant(restaurant);
    return "redirect:/hrms/restaurants";
}
//form for updating already existing hotel
@GetMapping("/edit/{restaurantId}")
public String updateRestaurantForm(@PathVariable Long restaurantId, Model model) {
    model.addAttribute("restaurant", restaurantService.findRestaurantById(restaurantId));
    return "restaurant_edit";
}

//update already existing hotel
@PostMapping("/{RestaurantId}")
public String updateRestaurant(@PathVariable Long RestaurantId, @ModelAttribute("restaurant") Restaurant restaurant, Model model) {
    // get hotel from database by id
    Restaurant existingRestaurant = restaurantService.findRestaurantById(RestaurantId);
    existingRestaurant.setRestaurnatId(RestaurantId);
    existingRestaurant.setName(restaurant.getName());
    existingRestaurant.setDistance(restaurant.getDistance());
    existingRestaurant.setDesc(restaurant.getDesc());
    existingRestaurant.setPhoto(restaurant.getPhoto());
    existingRestaurant.setAmenities(restaurant.getAmenities());
    existingRestaurant.setStar(restaurant.getStar());

    // save updated guest object
    restaurantService.updateRestaurant(existingRestaurant);
    return "redirect:/hrms/restaurants";
}
//handler method to handle delete guest request
@GetMapping("/{RestaurantId}")
public String deleteRestaurant(@PathVariable Long restaurantId) {
    restaurantService.deleteRestaurantById(restaurantId);
    return "redirect:/hrms/restaurants";
}
// view hotel details for particular hotel
@GetMapping("/details/{restaurantId}")
public String viewRestaurant(@PathVariable Long restaurantId, Model model) {
    model.addAttribute("restaurant", restaurantService.findRestaurantById(restaurantId));
    return "restaurant_view";
}

// view hotel details for particular hotel as user
@GetMapping("/usr/details/{restaurantId}")
public String viewRestaurantForUsers(@PathVariable Long restaurantId, Model model) {
    model.addAttribute("restaurant", restaurantService.findRestaurantById(restaurantId));
    return "restaurant_view_user";
}
}
