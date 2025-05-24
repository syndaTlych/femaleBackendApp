package com.example.female.controller;

import com.example.female.dto.RegisterRequest;
import com.example.female.entities.UserEntity;
import com.example.female.services.KeycloakAdminClientInterface;
import com.example.female.services.UserInterface;
import com.example.female.dto.SignupRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInterface userint;

    @Autowired
    private KeycloakAdminClientInterface keycloakService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserEntity request) {
        try {
            System.out.println("Signup request: " + request.getUsername() + " " + request.getPassword());

            userint.addUser(request);
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Map<String, Object>> getUser() {
        Map<String, Object> response = new HashMap<>();
        response.put("name", "synda");
        response.put("status", "success");
        response.put("data", "user details here");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public UserEntity addUser(@RequestBody UserEntity user) {
//        return userint.addUser(user);
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userint.deleteUser(id);
    }

    @DeleteMapping("/deleteuser")
    public void deleteUsers(@RequestParam Long id) {
        userint.deleteUser(id);
    }

    @PostMapping("/addlistusers")
    public List<UserEntity> addListUsers(@RequestBody List<UserEntity> users) {
        return userint.addListUsers(users);
    }

    @PostMapping("/confpassword")
    public String addUserWithConfirmedPassword(@RequestBody UserEntity user) {
        return userint.addUserWTCP(user);
    }

    @PostMapping("/adduserwtun")
    public String addUserWithUsernameOnly(@RequestBody UserEntity user) {
        return userint.addUserWTUN(user);
    }

    @PatchMapping("/updateuser/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        return userint.updateUser(id, user);
    }

    @GetMapping("/getallusers")
    public List<UserEntity> getAllUsers() {
        return userint.getAllUsers();
    }

    @GetMapping("/getuserbyid/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userint.getUserById(id);
    }

    @GetMapping("/getuserbyun/{un}")
    public List<UserEntity> getUserByUsername(@PathVariable String un) {
        return userint.getuserssw(un);
    }

    @GetMapping("/getuserbyemail")
    public List<UserEntity> getUserByEmail(@RequestParam("e") String email) {
        return userint.getusersbyemail(email);
    }
}
