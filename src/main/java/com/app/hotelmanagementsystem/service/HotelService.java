package com.app.hotelmanagementsystem.service;

import com.app.hotelmanagementsystem.entity.Hotel;
import com.app.hotelmanagementsystem.entity.Room;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface HotelService {

    List<Hotel> getAllHotels();
    Page<Hotel> getAllHotelsPage(Integer pageNumber, String sortField, String sortDirection);
    Hotel addNewHotel(Hotel hotel);
    Hotel findHotelById(Long hotelId);
    Set<Room> findRoomsInHotelByHotelId(Long hotelId);
    Hotel updateHotel(Hotel hotel);
    void deleteHotelById(Long hotelId);
}
