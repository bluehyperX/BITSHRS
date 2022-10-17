package com.app.hotelmanagementsystem.service;

import com.app.hotelmanagementsystem.entity.Guest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GuestService {
    List<Guest> getAllGuests();
    Page<Guest> getAllGuestsPage(Integer pageNumber, String sortField, String sortDirection);
    Guest addNewGuest(Guest guest);
    Guest findGuestById(Long guestId);
    Guest updateGuest(Guest guest);
    void deleteGuestById(Long guestId);
}
