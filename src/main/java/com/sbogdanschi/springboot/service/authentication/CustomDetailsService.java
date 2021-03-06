package com.sbogdanschi.springboot.service.authentication;

import com.sbogdanschi.springboot.repository.UserRepository;
import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sbogdanschi on 11/22/2017.
 */
@Service("customDetailsService")
public class CustomDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger(CustomDetailsService.class);

    private final UserRepository userRepository;


    public CustomDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
        LOGGER.debug("Retrieved user: {}  with authorities: {}", user, authorities);
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRoleType()));
        }

        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isActive(), true, true, true, authorities);
    }
}
