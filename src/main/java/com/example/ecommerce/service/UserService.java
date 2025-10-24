package com.example.ecommerce.service;

import com.example.ecommerce.model.User;
import com.example.ecommerce.dao.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    public boolean registerUser(User user) {
        if(userRepository.findByUsername(user.getUsername()) != null) return false;
        user.setRole("USER");
        userRepository.save(user);
        return true;
    }

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user != null && user.getPassword().equals(password)) return user;
        return null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
