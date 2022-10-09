package com.app.hotelmanagementsystem.service.impl;

import com.app.hotelmanagementsystem.entity.Guest;
import com.app.hotelmanagementsystem.repository.GuestRepository;
import com.app.hotelmanagementsystem.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    @Override
    public Guest addNewGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public Guest findGuestById(Long guestId) {
        return guestRepository.findById(guestId).get();
    }

    @Override
    public Guest updateGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public void deleteGuestById(Long guestId) {
        guestRepository.deleteById(guestId);
    }
}
