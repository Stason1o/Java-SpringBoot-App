package com.sbogdanschi.springboot.util;

import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.entity.dto.UserDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

public class SecurityContextInitializationHelper {

    public static void initializeSecurityContextWithSimpleUser() {
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1, "ADMIN", true));
        final UserDto authUser = UserDto.builder().id(2L).username("stas")
                .password("$2a$10$PwLXCu//RdcMvqcb62JIfexzVPRSjOnvuSc/Pu.d5ECWxSxHd7PHy")
                .active(true).roles(roles).build();
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authUser, null);
        securityContext.setAuthentication(authenticationToken);

    }

    public static Authentication getAuthentication(User user) {
        Set<GrantedAuthority> roles = new HashSet<>();
        user.getRoles().forEach(role -> roles.add(new SimpleGrantedAuthority(role.getRoleType())));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isActive(), true, true, true, roles);

        return new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
    }
}
