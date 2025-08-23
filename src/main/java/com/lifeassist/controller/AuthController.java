package com.lifeassist.controller;

import com.lifeassist.dto.AuthRequest;
import com.lifeassist.dto.AuthResponse;
import com.lifeassist.dto.RegisterRequest;
import com.lifeassist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    
    @GetMapping("/check")
    public String healthCheck() {
        return "Life Assist Auth Service is Working!";
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(userService.authenticate(request.getEmail(), request.getPassword()));
    }
}