package com.example.female.serviceimplement;

import com.example.female.entities.Role;
import com.example.female.entities.RoleName;
import com.example.female.entities.UserEntity;
import com.example.female.repository.RoleRepo;
import com.example.female.repository.UserRepo;
import com.example.female.services.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImplement implements UserInterface {
   @Autowired
   UserRepo userRepo;
   @Autowired
   RoleRepo RoleRepo;



    @Override
    public UserEntity addUser(UserEntity user) {
        // Assign default role if none provided
        if(user.getRoles() == null || user.getRoles().isEmpty()) {
            Role userRole = RoleRepo.findByName(RoleName.ROLE_USER)
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setName(RoleName.ROLE_USER);
                        return RoleRepo.save(newRole);
                    });
            user.getRoles().add(userRole);
        }

        return userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<UserEntity> addListUsers(List<UserEntity> listusers) {
        return userRepo.saveAll(listusers);
    }

    @Override
    public String addUserWTCP(UserEntity user) {
        String ch="";
        if (user.getPassword().equals(user.getConfirmPassword())){
            userRepo.save(user);
            ch="user added successfully";
        }
        else{
        ch="check confirm password";}
        return ch;
    }

    @Override
    public String addUserWTUN(UserEntity user) {
        String ch="";
        if (userRepo.existsByUsername((user.getUsername()))){
            ch="user already exists";
        }
        else{
            userRepo.save (user);
            ch="user added successfully";
        }
        return ch;
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity user) {
        UserEntity usr=userRepo.findById(id).get();
        usr.setFullName(user.getFullName());
        usr.setUsername(user.getUsername());
            return userRepo.save(user);

    }


    @Override
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserEntity getUserById(long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public List<UserEntity> getuserssw(String un) {
        return userRepo.GetUSERSW (un);
    }

    @Override
    public List<UserEntity> getusersbyemail(String email) {
        return userRepo.GetUSERSbyemail(email);
    }
    @Override
    public boolean userExistsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }
}
