package com.app.hotelmanagementsystem.controller;

import com.app.hotelmanagementsystem.entity.Guest;
import com.app.hotelmanagementsystem.service.impl.GuestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hms/guests")
public class GuestController {

    private final GuestServiceImpl guestService;

    @Autowired
    public GuestController(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public String listGuests(Model model) {
        model.addAttribute("guests", guestService.getAllGuests());
        return "guests";
    }

    @GetMapping("/new")
    public String addGuestForm(Model model) {
        Guest guest = new Guest();
        model.addAttribute("guest", guest);
        return "create_guest";
    }

    @PostMapping
    public String addNewGuest(@ModelAttribute("guest") Guest guest) {
        guestService.addNewGuest(guest);
        return "redirect:/hms/guests";
    }

    @GetMapping("/edit/{guestId}")
    public String updateGuestForm(@PathVariable Long guestId, Model model) {
        model.addAttribute("guest", guestService.findGuestById(guestId));
        return "edit_guest";
    }

    @PostMapping("/{guestId}")
    public String updateGuest(@PathVariable Long guestId, @ModelAttribute("guest") Guest guest, Model model) {
        // get guest from database by id
        Guest existingGuest = guestService.findGuestById(guestId);
        existingGuest.setGuestId(guestId);
        existingGuest.setFirstName(guest.getFirstName());
        existingGuest.setLastName(guest.getLastName());
        existingGuest.setEmailAddress(guest.getEmailAddress());
        existingGuest.setPhoneNumber(guest.getPhoneNumber());
        existingGuest.setIdNumber(guest.getIdNumber());
        existingGuest.setDateOfArriving(guest.getDateOfArriving());
        existingGuest.setTimeOfArriving(guest.getTimeOfArriving());

        // save updated guest object
        guestService.updateGuest(existingGuest);
        return "redirect:/hms/guests";
    }

    // handler method to handle delete guest request
    @GetMapping("/{guestId}")
    public String deleteGuest(@PathVariable Long guestId) {
        guestService.deleteGuestById(guestId);
        return "redirect:/hms/guests";
    }
}
