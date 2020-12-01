package com.example.AllEShop.services.implementations;

import com.example.AllEShop.entities.User;
import com.example.AllEShop.repositories.UserRepository;
import com.example.AllEShop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User myUser = userRepository.findByEmail(s);
        if (myUser!=null){
            org.springframework.security.core.userdetails.User secUser = new org.springframework.security.core.userdetails.User(myUser.getEmail(),myUser.getPassword(),myUser.getRoles());
            return secUser;
        }
        throw new UsernameNotFoundException("User not found");
    }
}
