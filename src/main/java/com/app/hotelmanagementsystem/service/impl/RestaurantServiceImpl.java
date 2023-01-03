package com.app.hotelmanagementsystem.service.impl;


import com.app.hotelmanagementsystem.entity.Restaurant;
import com.app.hotelmanagementsystem.entity.Tables;
import com.app.hotelmanagementsystem.repository.RestaurantRepository;
import com.app.hotelmanagementsystem.service.RestaurantService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	private final RestaurantRepository restaurantRepository;
	
	   public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
		   this.restaurantRepository = restaurantRepository;
	   }
	   
	   @Override
	    public List<Restaurant> getAllRestaurants() {
	        return restaurantRepository.findAll();
	    }

	    @Override
	    public Page<Restaurant> getAllRestaurantsPage(Integer pageNumber, String sortField, String sortDirection) {
	        Sort sort = Sort.by(sortField);
	        sort = sortDirection.equals("asc") ? sort.ascending() : sort.descending();
	        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
	        return restaurantRepository.findAll(pageable);
	    }

	    @Override
	    public Restaurant addNewRestaurant(Restaurant restaurant) {
	        return restaurantRepository.save(restaurant);
	    }

	    @Override
	    public Restaurant findRestaurantById(Long restaurantId) {
	        return restaurantRepository.findById(restaurantId).get();
	    }

	    @Override
	    public Set<Tables> findTablesInRestaurantByRestaurantId(Long restaurantId) {
	        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
	        return restaurant.getTables();
	    }

	    @Override
	    public Restaurant updateRestaurant(Restaurant restaurant) {
	        return restaurantRepository.save(restaurant);
	    }

	    @Override
	    public void deleteRestaurantById(Long restaurantId) {
	        restaurantRepository.deleteById(restaurantId);
	    }	   
}
