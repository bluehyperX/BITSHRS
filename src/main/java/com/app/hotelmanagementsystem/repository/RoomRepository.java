package com.app.hotelmanagementsystem.repository;

import com.app.hotelmanagementsystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    
}
