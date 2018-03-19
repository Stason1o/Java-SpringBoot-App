package com.sbogdanschi.springboot.service.impl;

import com.sbogdanschi.springboot.repository.RoleRepository;
import com.sbogdanschi.springboot.repository.UserRepository;
import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @Override
    public boolean userExists(String username) {
        return userRepository.isUsernameRegistered(username);
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return userRepository.isEmailRegistered(email);
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUserById(user.getId(), user.getUsername(), user.getEmail(),
                user.getFirstName(), user.getLastName());
    }

    public void updateUser1(User user) {
        userRepository.saveAndFlush(user);
    } //

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAllUsers();
    }

    @Override
    public List<User> findByUsernameOrEmail(String data) {
        return userRepository.findByUsernameOrEmail(data);
    }
}