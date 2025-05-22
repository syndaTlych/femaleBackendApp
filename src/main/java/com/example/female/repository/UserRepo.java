package com.example.female.repository;
import com.example.female.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository <UserEntity,Long> {
    boolean existsByUsername (String username);
    UserEntity findByEmail (String email);
    @Query("SELECT u from UserEntity u WHERE u.username=?1")
    UserEntity finduserByUsername(String username);
    @Query("SELECT CASE WHEN COUNT (u)>0 THEN true ELSE false end FROM UserEntity u WHERE u.username= :username")
    boolean existsByUsernamejpgl(@Param("username")String username);
    @Query(value="SELECT * FROM U=users u WHERE u.username=?1" , nativeQuery =true )
    UserEntity findByUsernameSQL(String username);
    @Query (value = "SELECT COUNT(*) FROM users WHERE username ",nativeQuery = true)
    boolean existsbyUsernameSql(@Param("username")String username);
    UserEntity findByUsername(String username);
    @Query(value="select *from users u where u.username like :cle%" , nativeQuery = true)
    List<UserEntity> GetUSERSW(@Param("cle")String un);
    @Query(value="select * from users u where u.email like %:domaine%" , nativeQuery = true)
    List<UserEntity> GetUSERSbyemail(@Param("domaine")String un);



}
