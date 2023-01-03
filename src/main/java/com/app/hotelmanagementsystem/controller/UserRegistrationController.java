package com.app.hotelmanagementsystem.controller;

import com.app.hotelmanagementsystem.entity.User;
import com.app.hotelmanagementsystem.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hrms/registration")
public class UserRegistrationController {

    private final CustomUserDetailsServiceImpl userService;

    @Autowired
    public UserRegistrationController(CustomUserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") User user) {
        userService.registerNewUser(user);
        return "redirect:/hrms/login";
    }
}
