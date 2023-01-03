package com.app.hotelmanagementsystem.controller;

import com.app.hotelmanagementsystem.entity.Hotel;
import com.app.hotelmanagementsystem.entity.Room;
import com.app.hotelmanagementsystem.service.impl.HotelServiceImpl;
import com.app.hotelmanagementsystem.service.impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hrms/rooms")
public class RoomController {

    private final RoomServiceImpl roomService;
    private final HotelServiceImpl hotelService;

    @Autowired
    public RoomController(RoomServiceImpl roomService, HotelServiceImpl hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

//    @GetMapping
//    public String listRooms(Model model) {
//        model.addAttribute("rooms", roomService.getAllRooms());
//        return "rooms";
//    }

    @GetMapping
    public String listRooms(Model model) {
        return listRoomsByPage(model, 1, "name", "asc");
    }

    @GetMapping("/page/{pageNumber}")
    public String listRoomsByPage(Model model, @PathVariable("pageNumber") Integer currentPage,
                                   @Param("sortField") String sortField, @Param("sortDirection") String sortDirection) {
        Page<Room> page = roomService.getAllRoomsPage(currentPage, sortField, sortDirection);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Room> listRooms = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("rooms", listRooms);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);
        return "rooms";
    }

    @GetMapping("/new")
    public String addRoomForm(Model model) {
        Room room = new Room();
        List<Hotel> listHotels = hotelService.getAllHotels();

        model.addAttribute("room", room);
        model.addAttribute("listHotels", listHotels);
        return "room_create";
    }

    @PostMapping
    public String addNewRoom(@ModelAttribute("room") Room room) {
        roomService.addNewRoom(room);
        return "redirect:/hrms/rooms";
    }

    @GetMapping("/edit/{roomId}")
    public String updateRoomForm(@PathVariable Long roomId, Model model) {
        List<Hotel> listHotels = hotelService.getAllHotels();

        model.addAttribute("room", roomService.findRoomById(roomId));
        model.addAttribute("listHotels", listHotels);
        return "room_edit";
    }

    @PostMapping("/{roomId}")
    public String updateRoom(@PathVariable Long roomId, @ModelAttribute("room") Room room) {
        // get room from database by id
        Room existingRoom = roomService.findRoomById(roomId);
        existingRoom.setRoomId(roomId);
        existingRoom.setName(room.getName());
        existingRoom.setPrice(room.getPrice());
        existingRoom.setHotel(room.getHotel());
        existingRoom.setType(room.getType());

        // save updated room object
        roomService.updateRoom(existingRoom);
        return "redirect:/hrms/rooms";
    }

    // handler method to handle delete room request
    @GetMapping("/{roomId}")
    public String deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoomById(roomId);
        return "redirect:/hrms/rooms";
    }

    @GetMapping("/details/{roomId}")
    public String viewRoom(@PathVariable Long roomId, Model model) {
        model.addAttribute("room", roomService.findRoomById(roomId));
        return "room_view";
    }
}
