package com.app.hotelmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/hms/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String homePage() {
        return "guests";
    }
}
