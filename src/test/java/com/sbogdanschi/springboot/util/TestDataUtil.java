package com.sbogdanschi.springboot.util;

import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.entity.dto.RoleDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by sbogdanschi on 11/13/2017.
 */
@Component
public class TestDataUtil {

    private static final String ADMIN = "ADMIN";

    private static final String USER = "USER";

    private TestDataUtil(){}

    public static User buildUser() {
        return User.builder().id(1L)
                .username("username")
                .password("password")
                .repeatPassword("repeatPassword")
                .firstName("firstName")
                .lastName("lastName")
                .active(true)
                .email("email@domain.com")
                .roles(buildRoleList()).build();
    }

    public static User buildUser(String username) {
        return User.builder()
                .username(username)
                .password("password")
                .repeatPassword("repeatPassword")
                .firstName("firstName")
                .lastName("lastName")
                .active(true)
                .email("qwerty@domain.com")
                .roles(buildRoleList()).build();
    }

    public static User buildUserWithoutRoles(String username) {
        return User.builder()
                .username(username)
                .password("password")
                .repeatPassword("repeatPassword")
                .firstName("firstName")
                .lastName("lastName")
                .active(true)
                .email("qwerty@domain.com")
                .build();
    }

    public static User buildValidUser() {
        return User.builder().id(3L)
                .username("Stason1o")
                .password("$2a$10$PwLXCu//RdcMvqcb62JIfexzVPRSjOnvuSc/Pu.d5ECWxSxHd7PHy")
                .firstName("firstName")
                .lastName("lastName")
                .active(true)
                .email("email@domain.com")
                .roles(buildRoleList()).build();
    }

    public static User buildValidUserWithId(Long id) {
        return User.builder().id(id)
                .username("Stason1o")
                .password("$2a$10$PwLXCu//RdcMvqcb62JIfexzVPRSjOnvuSc/Pu.d5ECWxSxHd7PHy")
                .firstName("firstName")
                .lastName("lastName")
                .active(true)
                .email("email@domain.com")
                .roles(buildRoleList()).build();
    }

    public static List<User> buildUserList() {
        List<User> users = new ArrayList<>();
        users.add(buildValidUserWithId(1L));
        users.add(buildValidUserWithId(2L));
        users.add(buildValidUserWithId(3L));
        return users;
    }

    public static Role buildRole(int id, String roleType) {
        return Role.builder().id(id).roleType(roleType).active(false).build();
    }

    public static Role buildRole(String roleType) {
        return Role.builder().roleType(roleType).active(false).build();
    }

    public static RoleDto buildRoleDto(int id, String roleType) {
        return RoleDto.builder().id(id).roleType(roleType).active(false).build();
    }

    public static Set<Role> buildRoleList() {
        Set<Role> roles = new HashSet<>();
        roles.add(buildRole(1, ADMIN));
        roles.add(buildRole(2, USER));
        return roles;
    }
    public static Set<Role> buildRoleListWithoutIds() {
        Set<Role> roles = new HashSet<>();
        roles.add(buildRole(ADMIN));
        roles.add(buildRole(USER));
        return roles;
    }


    public static UserDetails buildUserForAuthentication(User user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleType())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isActive(), true, true, true, grantedAuthorities);
    }
}
