package com.app.hotelmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.hotelmanagementsystem.entity.Restaurant;
import com.app.hotelmanagementsystem.entity.Tables;
import com.app.hotelmanagementsystem.service.impl.RestaurantServiceImpl;
import com.app.hotelmanagementsystem.service.impl.TableServiceImpl;


@Controller
@RequestMapping("/hrms/tables")
public class TablesController {
	
	private final TableServiceImpl tableService;
	private final RestaurantServiceImpl restaurantService;

    @Autowired
    public TablesController(TableServiceImpl tableService, RestaurantServiceImpl restaurantService) {
        this.tableService = tableService;
        this.restaurantService = restaurantService;
    }

//    @GetMapping
//    public String listRooms(Model model) {
//        model.addAttribute("rooms", roomService.getAllRooms());
//        return "rooms";
//    }

    @GetMapping
    public String listTables(Model model) {
        return listTablesByPage(model, 1, "name", "asc");
    }

    @GetMapping("/page/{pageNumber}")
    public String listTablesByPage(Model model, @PathVariable("pageNumber") Integer currentPage,
                                   @Param("sortField") String sortField, @Param("sortDirection") String sortDirection) {
        Page<Tables> page = tableService.getAllTablesPage(currentPage, sortField, sortDirection);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Tables> listTables = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("tables", listTables);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);
        return "tables";
    }

    @GetMapping("/new")
    public String addTableForm(Model model) {
        Tables table = new Tables();
        List<Restaurant> listRestaurants = restaurantService.getAllRestaurants();

        model.addAttribute("table", table);
        model.addAttribute("listRestaurants", listRestaurants);
        return "table_create";
    }

    @PostMapping
    public String addNewTable(@ModelAttribute("table") Tables table) {
    	tableService.addNewTable(table);
        return "redirect:/hrms/tables";
    }

    @GetMapping("/edit/{tableId}")
    public String updateTableForm(@PathVariable Long tableId, Model model) {
        List<Restaurant> listRestaurants = restaurantService.getAllRestaurants();

        model.addAttribute("table", tableService.findTableById(tableId));
        model.addAttribute("listRestaurants", listRestaurants);
        return "table_edit";
    }

    @PostMapping("/{tableId}")
    public String updateTable(@PathVariable Long tableId, @ModelAttribute("table") Tables table) {
        // get room from database by id
        Tables existingTable = tableService.findTableById(tableId);
        existingTable.setTableId(tableId);
        existingTable.setType(table.getType());
        existingTable.setPrice(table.getPrice());
        existingTable.setRestaurant(table.getRestaurant());
        

        // save updated room object
        tableService.updateTable(existingTable);
        return "redirect:/hrms/tables";
    }

    // handler method to handle delete room request
    @GetMapping("/{tableId}")
    public String deleteTable(@PathVariable Long tableId) {
        tableService.deleteTableById(tableId);
        return "redirect:/hrms/tables";
    }

    @GetMapping("/details/{tableId}")
    public String viewTable(@PathVariable Long tableId, Model model) {
        model.addAttribute("table", tableService.findTableById(tableId));
        return "table_view";
    }

}
