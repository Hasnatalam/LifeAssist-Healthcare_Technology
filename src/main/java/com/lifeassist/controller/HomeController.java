package com.lifeassist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {
    
    @GetMapping
    public ResponseEntity<String> home(Authentication authentication) {
        String username = authentication.getName();
        
        // You can also get the user ID from the principal if you stored it
        // For now, we'll just return the username
        return ResponseEntity.ok("Welcome to Life Assist! Your username is: " + username);
    }

    @GetMapping("/user-info")
    public ResponseEntity<String> getUserInfo(Authentication authentication) {
        String username = authentication.getName();
        String authorities = authentication.getAuthorities().toString();
        
        return ResponseEntity.ok("Username: " + username + "\nAuthorities: " + authorities);
    }
}