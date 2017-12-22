package com.sbogdanschi.springboot.service;

import com.sbogdanschi.springboot.entity.User;

import java.util.List;

public interface UserService {
    User findUserByEmail(String email);

    User findByUsername(String username);

    void saveUser(User user);

    List<User> retrieveAllUsers();

    boolean userExists();

    User findById(Long id);

    void updateUser(User user);

    void deleteUserById(Long id);

    void deleteAllUsers();

    void updateUser1(User user);

}
