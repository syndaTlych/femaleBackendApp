package com.example.female.services;

import com.example.female.entities.UserEntity;

import java.util.List;

public interface UserInterface {
    public UserEntity addUser (UserEntity user);
    void deleteUser (Long id);
    List<UserEntity> addListUsers(List<UserEntity> listusers);
    public String addUserWTCP (UserEntity user);
    public String addUserWTUN (UserEntity user);
    public UserEntity updateUser (Long id, UserEntity user);
    public List<UserEntity> getAllUsers();
    UserEntity getUserById (long id);
    List<UserEntity> getuserssw(String un);
    List<UserEntity> getusersbyemail(String email);
    boolean userExistsByUsername(String username);

}
