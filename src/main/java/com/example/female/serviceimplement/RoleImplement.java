package com.example.female.serviceimplement;


import com.example.female.entities.Role;
import com.example.female.repository.RoleRepo;
import com.example.female.services.RoleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleImplement implements RoleInterface {
    @Autowired
    private RoleRepo roleRepo;
   @Override
   public Role add(Role role){
        return roleRepo.save(role);
    }
}
