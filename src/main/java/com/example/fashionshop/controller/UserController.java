package com.example.fashionshop.controller;

import com.example.fashionshop.model.User;
import com.example.fashionshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    List<User> getAll(){
        return userRepository.findAll();
    }
}
