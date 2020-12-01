package com.example.AllEShop.services;

import com.example.AllEShop.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

public interface UserService  extends UserDetailsService {

    User getUserByEmail(String email);
    User getUserById(Long id);
    void deleteUserById(Long id);
    void saveUser(User user);
    ArrayList<User> getAllUsers();
}
