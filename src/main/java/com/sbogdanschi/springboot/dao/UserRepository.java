package com.sbogdanschi.springboot.dao;

import com.sbogdanschi.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username LIKE %?1 or u.email LIKE %?1")
    List<User> findByUsernameOrEmail(String data);

    List<User> findAll();

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username")
    boolean userExists(@Param("username") String username);

    User findById(Long id);

    @Modifying
    @Query("Update User u set u.username = ?2, u.email = ?3, u.firstName = ?4, u.lastName = ?5 where u.id = ?1")
    void updateUser(Long userId, String username, String email, String firstName, String lastName );

    void deleteUserById(Long id);

    @Query("DELETE from User")
    void deleteAllUsers();

}
