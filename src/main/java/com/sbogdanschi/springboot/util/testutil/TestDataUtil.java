package com.sbogdanschi.springboot.util.testutil;

import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sbogdanschi on 11/13/2017.
 */
@Component
public class TestDataUtil {

    private TestDataUtil(){}

    public static User buildUser() {
        Role role = new Role();
        role.setId(2);
        role.setRoleType("USER");

        Set<Role> set = new HashSet<>();
        set.add(role);
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("password");
        user.setLastName("lastName");
        user.setFirstName("firstName");
        user.setActive(true);
        user.setEmail("email@domain.com");
        user.setRoles(set);
        return user;
    }
}
