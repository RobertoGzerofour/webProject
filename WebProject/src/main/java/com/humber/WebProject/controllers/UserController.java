package com.humber.WebProject.controllers;

import com.humber.WebProject.config.JwtUtil;
import com.humber.WebProject.models.User;
import com.humber.WebProject.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        userService.saveUser(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully!");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        User authenticatedUser = userService.authenticate(user.getEmail(), user.getPassword());

        Map<String, String> response = new HashMap<>();

        if (authenticatedUser != null) {
            String token = jwtUtil.generateToken(authenticatedUser.getEmail(),authenticatedUser.getFirst_name());
            response.put("token", token);
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Invalid email or password");
            return ResponseEntity.status(401).body(response);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<Map<String, String>> getUserProfile(@RequestHeader("Authorization") String token) {
        String email = jwtUtil.getEmailFromToken(token);
        Optional<User> user = userService.getUserByEmail(email);

        Map<String, String> response = new HashMap<>();

        if (user.isPresent()) {
            response.put("message", "Welcome, " + user.get().getFirst_name() + "!");
            response.put("email", user.get().getEmail());
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Unauthorized");
            return ResponseEntity.status(401).body(response);
        }
    }
}
