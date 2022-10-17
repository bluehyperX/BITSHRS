package com.app.hotelmanagementsystem.controller;

import com.app.hotelmanagementsystem.entity.Guest;
import com.app.hotelmanagementsystem.entity.Hotel;
import com.app.hotelmanagementsystem.service.impl.GuestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hms/guests")
public class GuestController {

    private final GuestServiceImpl guestService;

    @Autowired
    public GuestController(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }

//    @GetMapping
//    public String listGuests(Model model) {
//        model.addAttribute("guests", guestService.getAllGuests());
//        return "guests";
//    }

    @GetMapping
    public String listGuests(Model model) {
        return listGuestsByPage(model, 1, "firstName", "asc");
    }

    @GetMapping("/page/{pageNumber}")
    public String listGuestsByPage(Model model, @PathVariable("pageNumber") Integer currentPage,
                                   @Param("sortField") String sortField, @Param("sortDirection") String sortDirection) {
        Page<Guest> page = guestService.getAllGuestsPage(currentPage, sortField, sortDirection);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Guest> listGuests = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("guests", listGuests);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);
        return "guests";
    }

    @GetMapping("/new")
    public String addGuestForm(Model model) {
        Guest guest = new Guest();
        model.addAttribute("guest", guest);
        return "guest_create";
    }

    @PostMapping
    public String addNewGuest(@ModelAttribute("guest") Guest guest) {
        guestService.addNewGuest(guest);
        return "redirect:/hms/guests";
    }

    @GetMapping("/edit/{guestId}")
    public String updateGuestForm(@PathVariable Long guestId, Model model) {
        model.addAttribute("guest", guestService.findGuestById(guestId));
        return "guest_edit";
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
        existingGuest.setCheckInDate(guest.getCheckInDate());
        existingGuest.setCheckInTime(guest.getCheckInTime());

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

    @GetMapping("/details/{guestId}")
    public String viewGuest(@PathVariable Long guestId, Model model) {
        model.addAttribute("guest", guestService.findGuestById(guestId));
        return "guest_view";
    }
}
