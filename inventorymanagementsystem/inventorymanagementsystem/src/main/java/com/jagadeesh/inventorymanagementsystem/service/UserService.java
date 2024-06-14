package com.jagadeesh.inventorymanagementsystem.service;


import com.jagadeesh.inventorymanagementsystem.entity.User;
import com.jagadeesh.inventorymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            return user;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        user.setCreatedAt(new Date());
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

}
