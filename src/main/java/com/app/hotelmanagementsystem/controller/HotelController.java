package com.app.hotelmanagementsystem.controller;

import com.app.hotelmanagementsystem.entity.Hotel;
import com.app.hotelmanagementsystem.service.impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hms/hotels")
public class HotelController {

    private final HotelServiceImpl hotelService;

    @Autowired
    public HotelController(HotelServiceImpl hotelService) {
        this.hotelService = hotelService;
    }


    // shows a list of hotels - redirect to listHotelsByPage
    @GetMapping
    public String listHotels(Model model) {
        return listHotelsByPage(model, 1, "name", "asc");
    }

    // shows a list of hotels paging
    @GetMapping("/page/{pageNumber}")
    public String listHotelsByPage(Model model, @PathVariable("pageNumber") Integer currentPage,
                                   @Param("sortField") String sortField, @Param("sortDirection") String sortDirection) {
        Page<Hotel> page = hotelService.getAllHotelsPage(currentPage, sortField, sortDirection);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Hotel> listHotels = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("hotels", listHotels);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);
        return "hotels";
    }

    // shows a list of hotels for users - redirect to listHotelsByPageForUsers
    @GetMapping("/usr")
    public String listHotelsForUsers(Model model) {
        return listHotelsByPageForUsers(model, 1, "name", "asc");
    }

    //shows a list of hotels for users paging
    @GetMapping("/usr/page/{pageNumber}")
    public String listHotelsByPageForUsers(Model model, @PathVariable("pageNumber") Integer currentPage,
                                   @Param("sortField") String sortField, @Param("sortDirection") String sortDirection) {
        Page<Hotel> page = hotelService.getAllHotelsPage(currentPage, sortField, sortDirection);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Hotel> listHotels = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("hotels", listHotels);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);
        return "hotels_user";
    }

    // shows rooms within a specific hotel
    @GetMapping("/{hotelId}/rooms")
    public String listRoomsInHotel(@PathVariable Long hotelId, Model model) {
        model.addAttribute("rooms", hotelService.findRoomsInHotelByHotelId(hotelId));
        return "rooms_in_hotel";
    }

    // shows rooms within a specific hotel for users
    @GetMapping("/usr/{hotelId}/rooms")
    public String listRoomsInHotelForUsers(@PathVariable Long hotelId, Model model) {
        model.addAttribute("rooms", hotelService.findRoomsInHotelByHotelId(hotelId));
        return "rooms_in_hotel_user";
    }

    // form for creating a new hotel
    @GetMapping("/new")
    public String addHotelForm(Model model) {
        Hotel hotel = new Hotel();
        model.addAttribute("hotel", hotel);
        return "hotel_create";
    }

    // creates a new hotel
    @PostMapping
    public String addNewHotel(@ModelAttribute("hotel") Hotel hotel) {
        hotelService.addNewHotel(hotel);
        return "redirect:/hms/hotels";
    }

    // form for updating already existing hotel
    @GetMapping("/edit/{hotelId}")
    public String updateHotelForm(@PathVariable Long hotelId, Model model) {
        model.addAttribute("hotel", hotelService.findHotelById(hotelId));
        return "hotel_edit";
    }

    // update already existing hotel
    @PostMapping("/{hotelId}")
    public String updateHotel(@PathVariable Long hotelId, @ModelAttribute("hotel") Hotel hotel, Model model) {
        // get hotel from database by id
        Hotel existingHotel = hotelService.findHotelById(hotelId);
        existingHotel.setHotelId(hotelId);
        existingHotel.setName(hotel.getName());
        existingHotel.setStar(hotel.getStar());
        existingHotel.setLocation(hotel.getLocation());

        // save updated guest object
        hotelService.updateHotel(existingHotel);
        return "redirect:/hms/hotels";
    }

    // handler method to handle delete guest request
    @GetMapping("/{hotelId}")
    public String deleteHotel(@PathVariable Long hotelId) {
        hotelService.deleteHotelById(hotelId);
        return "redirect:/hms/hotels";
    }

    // view hotel details for particular hotel
    @GetMapping("/details/{hotelId}")
    public String viewHotel(@PathVariable Long hotelId, Model model) {
        model.addAttribute("hotel", hotelService.findHotelById(hotelId));
        return "hotel_view";
    }

    // view hotel details for particular hotel as user
    @GetMapping("/usr/details/{hotelId}")
    public String viewHotelForUsers(@PathVariable Long hotelId, Model model) {
        model.addAttribute("hotel", hotelService.findHotelById(hotelId));
        return "hotel_view_user";
    }
}