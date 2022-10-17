package com.app.hotelmanagementsystem.service;

import com.app.hotelmanagementsystem.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    Page<Employee> getAllEmployeesPage(Integer pageNumber, String sortField, String sortDirection);
    Employee addNewEmployee(Employee employee);
    Employee findEmployeeById(Long employeeId);
    Employee updateEmployee(Employee employee);
    void deleteEmployeeById(Long employeeId);
}
