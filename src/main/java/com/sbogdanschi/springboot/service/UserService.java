package com.sbogdanschi.springboot.service;

import com.sbogdanschi.springboot.entity.User;

public interface UserService {
    User findUserByEmail(String email);

    User findByUsername(String username);

    void saveUser(User user);

}
