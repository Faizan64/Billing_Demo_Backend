package com.example.billingdemo.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.billingdemo.entity.User;
import com.example.billingdemo.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private UserService userService;

    // Register API
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.registerUser(user) != null) {
            return ResponseEntity.ok("Registration successful");
        }
        return ResponseEntity.badRequest().body("Registration failed");
    }

    // Login API
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        Optional<User> user = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

}
