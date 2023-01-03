package com.app.hotelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.hotelmanagementsystem.entity.Restaurant;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
