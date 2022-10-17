package com.app.hotelmanagementsystem.controller;

import com.app.hotelmanagementsystem.entity.Employee;
import com.app.hotelmanagementsystem.entity.Guest;
import com.app.hotelmanagementsystem.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hms/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

//    @GetMapping
//    public String listEmployees(Model model) {
//        model.addAttribute("employees", employeeService.getAllEmployees());
//        return "employees";
//    }

    @GetMapping
    public String listEmployees(Model model) {
        return listEmployeesByPage(model, 1, "firstName", "asc");
    }

    @GetMapping("/page/{pageNumber}")
    public String listEmployeesByPage(Model model, @PathVariable("pageNumber") Integer currentPage,
                                   @Param("sortField") String sortField, @Param("sortDirection") String sortDirection) {
        Page<Employee> page = employeeService.getAllEmployeesPage(currentPage, sortField, sortDirection);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("employees", listEmployees);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);
        return "employees";
    }

    @GetMapping("/new")
    public String addEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee_create";
    }

    @PostMapping
    public String addNewEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.addNewEmployee(employee);
        return "redirect:/hms/employees";
    }

    @GetMapping("/edit/{employeeId}")
    public String updateGuestForm(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employee", employeeService.findEmployeeById(employeeId));
        return "employee_edit";
    }

    @PostMapping("/{employeeId}")
    public String updateEmployee(@PathVariable Long employeeId, @ModelAttribute("employee") Employee employee, Model model) {
        // get guest from database by id
        Employee existingEmployee = employeeService.findEmployeeById(employeeId);
        existingEmployee.setEmployeeId(employeeId);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setDateOfBirth(employee.getDateOfBirth());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setEmailAddress(employee.getEmailAddress());
        existingEmployee.setIdNumber(employee.getIdNumber());
        existingEmployee.setNationalIdentificationNumber(employee.getNationalIdentificationNumber());
        existingEmployee.setPlaceOfResidence(employee.getPlaceOfResidence());
        existingEmployee.setStreetAndNumberOfResidence(employee.getStreetAndNumberOfResidence());
        existingEmployee.setJobPosition(employee.getJobPosition());
        existingEmployee.setJobStartDate(employee.getJobStartDate());
        existingEmployee.setContractExpirationDate(employee.getContractExpirationDate());
        existingEmployee.setNotes(employee.getNotes());

        // save updated guest object
        employeeService.updateEmployee(existingEmployee);
        return "redirect:/hms/employees";
    }

    @GetMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return "redirect:/hms/employees";
    }

    @GetMapping("/details/{employeeId}")
    public String viewEmployee(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employee", employeeService.findEmployeeById(employeeId));
        return "employee_view";
    }
}
