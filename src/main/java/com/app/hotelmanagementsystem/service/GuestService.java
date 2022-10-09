package com.app.hotelmanagementsystem.service;

import com.app.hotelmanagementsystem.entity.Guest;

import java.util.List;

public interface GuestService {
    List<Guest> getAllGuests();
    Guest addNewGuest(Guest guest);
    Guest findGuestById(Long guestId);
    Guest updateGuest(Guest guest);
    void deleteGuestById(Long guestId);
}
