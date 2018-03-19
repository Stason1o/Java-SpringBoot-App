package com.sbogdanschi.springboot.repository;

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
    boolean isUsernameRegistered(@Param("username") String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email")
    boolean isEmailRegistered(@Param("email") String email);

    User findById(Long id);

    @Modifying
    @Query("UPDATE User u SET u.username = :username, u.email = :email, u.firstName = :firstName, u.lastName = :lastName where u.id = :userId")
    void updateUserById(@Param("userId") Long userId,
                        @Param("username") String username,
                        @Param("email") String email,
                        @Param("firstName") String firstName,
                        @Param("lastName") String lastName);

    @Modifying
    @Query("Update User u set u.username = ?2, u.email = ?3, u.firstName = ?4, u.lastName = ?5 where u.id = ?1")
    void updateUserByUsername(Long userId, String username, String email, String firstName, String lastName);

    void deleteUserById(Long id);

    @Query("DELETE from User")
    void deleteAllUsers();

}
