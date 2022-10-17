package com.app.hotelmanagementsystem.service;

import com.app.hotelmanagementsystem.entity.Role;
import com.app.hotelmanagementsystem.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    User findUserById(Long userId);
    User findUserByEmailAddress(String emailAddress);
    User registerNewUser(User user);
    void saveUserWithRole(User user);
    void deleteUserById(Long userId);
    List<Role> getRoles();

}
