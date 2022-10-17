package com.app.hotelmanagementsystem.service;

import com.app.hotelmanagementsystem.entity.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
    Page<Room> getAllRoomsPage(Integer pageNumber, String sortField, String sortDirection);
    Room addNewRoom(Room room);
    Room findRoomById(Long roomId);
    Room updateRoom(Room room);
    void deleteRoomById(Long roomId);
}
