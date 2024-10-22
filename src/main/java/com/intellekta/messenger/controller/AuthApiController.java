package com.intellekta.messenger.controller;

import com.intellekta.messenger.dto.LoginDto;
import com.intellekta.messenger.dto.RegisterDto;
import com.intellekta.messenger.entity.User;
import com.intellekta.messenger.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    private final UserRepository userRepository;

    @Autowired
    public AuthApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDto loginDto, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        User user = userRepository.findByUsername(loginDto.getUsername());
        if (user != null && user.getPassword().equals(loginDto.getPassword())) {
            session.setAttribute("loggedInUser", user);
            response.put("success", true);
            response.put("message", "Login successful!");
            return ResponseEntity.ok(response);
        }
        response.put("success", false);
        response.put("message", "Invalid username or password.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterDto registerDto) {
        Map<String, Object> response = new HashMap<>();
        if (userRepository.findByUsername(registerDto.getUsername()) == null) {
            User newUser = new User();
            newUser.setUsername(registerDto.getUsername());
            newUser.setPassword(registerDto.getPassword()); // Password hashing needed in production
            userRepository.save(newUser);
            response.put("success", true);
            response.put("message", "Registration successful!");
            return ResponseEntity.ok(response);
        }
        response.put("success", false);
        response.put("message", "User with this username already exists.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}


