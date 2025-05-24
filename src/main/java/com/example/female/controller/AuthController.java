package com.example.female.controller;

import com.example.female.dto.RegisterRequest;
import com.example.female.dto.SignupRequest;
import com.example.female.entities.UserEntity;
import com.example.female.serviceimplement.UserImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "*") // pour Flutter
public class AuthController {

    @Autowired
    private UserImplement userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match.");
        }

        if (userService.userExistsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("User already exists.");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setConfirmPassword(request.getConfirmPassword());
        user.setFullName(request.getFullName());

        userService.addUser(user);

        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignupRequest request) {
        if (!request.getPassword().equals(request.getPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match.");
        }
//
//        if (userService.userExistsByUsername(request.getUsername())) {
//            return ResponseEntity.badRequest().body("User already exists.");
//        }
//
//        UserEntity user = new UserEntity();
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setConfirmPassword(""));
//        user.setFullName("");
//
//        userService.addUser(user);

        return ResponseEntity.ok("User logged successfully.");
    }


}
