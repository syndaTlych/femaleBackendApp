package com.example.female.controller;

import com.example.female.entities.Role;
import com.example.female.repository.RoleRepo;
import com.example.female.services.RoleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class roleController {
    @Autowired
    private RoleInterface roleInterface;
    @PostMapping("/add")

    public Role addRole(@RequestBody Role role) {
        return roleInterface.add(role);
    }
}
