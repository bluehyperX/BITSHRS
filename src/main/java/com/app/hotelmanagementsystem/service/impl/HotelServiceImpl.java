package com.app.hotelmanagementsystem.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.hotelmanagementsystem.entity.Hotel;
import com.app.hotelmanagementsystem.entity.Room;
import com.app.hotelmanagementsystem.repository.HotelRepository;
import com.app.hotelmanagementsystem.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
  
    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Page<Hotel> getAllHotelsPage(Integer pageNumber, String sortField, String sortDirection) {
        Sort sort = Sort.by(sortField);
        sort = sortDirection.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
        return hotelRepository.findAll(pageable);
    }

    @Override
    public Hotel addNewHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel findHotelById(Long hotelId) {
        return hotelRepository.findById(hotelId).get();
    }

    @Override
    public Set<Room> findRoomsInHotelByHotelId(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).get();
        return hotel.getRooms();
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotelById(Long hotelId) {
        hotelRepository.deleteById(hotelId);
    }
  
}
