package com.app.hotelmanagementsystem.repository;

import com.app.hotelmanagementsystem.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
