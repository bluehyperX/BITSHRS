package com.app.hotelmanagementsystem.repository;

import com.app.hotelmanagementsystem.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
