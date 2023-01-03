package com.app.hotelmanagementsystem.service;
import com.app.hotelmanagementsystem.entity.Restaurant;
import com.app.hotelmanagementsystem.entity.Tables;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;





public interface RestaurantService {
List<Restaurant> getAllRestaurants();
Page<Restaurant> getAllRestaurantsPage(Integer pageNumber, String sortField, String sortDirection);
Restaurant addNewRestaurant(Restaurant restaurant);
Restaurant findRestaurantById(Long restaurantId);
Set<Tables>findTablesInRestaurantByRestaurantId(Long restaurantId);
Restaurant updateRestaurant(Restaurant restaurant);
void deleteRestaurantById(Long restaurantId);
}
