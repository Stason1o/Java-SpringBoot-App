package com.sbogdanschi.springboot.service.impl;

import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.dao.RoleRepository;
import com.sbogdanschi.springboot.dao.UserRepository;
import com.sbogdanschi.springboot.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final  Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findUserByEmail(String email) {
        LOGGER.debug("Retrieved user: " + email);
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        LOGGER.debug("Retrieved user: " + username);
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRoleType("USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);
        LOGGER.debug("User was successfully saved");
    }

    @Override
    public List<User> retrieveAllUsers() {
        LOGGER.debug("Retrieving list of users..");
        return userRepository.findAll();
    }
}