package com.app.hotelmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/hrms/login")
    public String login() {
        return "login";
    }

    @GetMapping("/hrms")
    public String homePage() {
        return "redirect:/hrms/home";
    }
}
