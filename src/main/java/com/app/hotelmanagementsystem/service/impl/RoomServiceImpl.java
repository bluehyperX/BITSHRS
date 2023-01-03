package com.app.hotelmanagementsystem.service.impl;
import com.app.hotelmanagementsystem.entity.Guest;
import com.app.hotelmanagementsystem.entity.Room;
import com.app.hotelmanagementsystem.repository.RoomRepository;
import com.app.hotelmanagementsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Page<Room> getAllRoomsPage(Integer pageNumber, String sortField, String sortDirection) {
        Sort sort = Sort.by(sortField);
        sort = sortDirection.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
        return roomRepository.findAll(pageable);
    }

    @Override
    public Room addNewRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room findRoomById(Long roomId) {
        return roomRepository.findById(roomId).get();
    }

    @Override
    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoomById(Long roomId) {
        roomRepository.deleteById(roomId);
    }
}
