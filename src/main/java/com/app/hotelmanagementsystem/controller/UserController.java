package com.app.hotelmanagementsystem.controller;

import com.app.hotelmanagementsystem.entity.Role;
import com.app.hotelmanagementsystem.entity.User;
import com.app.hotelmanagementsystem.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hms/users")
public class UserController {

    private final CustomUserDetailsServiceImpl userService;

    @Autowired
    public UserController(CustomUserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/details/{userId}")
    public String viewUser(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.findUserById(userId));
        return "user_view";
    }

    @GetMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return "redirect:/hms/users";
    }

    @GetMapping("/new")
    public String registerNewUserFormFromApp(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user_create";
    }

    @PostMapping("/new")
    public String registerNewUserFromApp(@ModelAttribute("user") User user) {
        userService.registerNewUser(user);
        return "redirect:/hms/users";
    }

    @GetMapping("/edit/{userId}")
    public String updateUserForm(@PathVariable Long userId, Model model) {
        User user = userService.findUserById(userId);
        List<Role> listRoles = userService.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "user_edit";
    }

    @PostMapping("/edit/save")
    public String saveUser(User user) {
        userService.saveUserWithRole(user);
        return "redirect:/hms/users";
    }
}
