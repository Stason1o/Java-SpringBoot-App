package com.sbogdanschi.springboot.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class ControllerUtils {

    private ControllerUtils(){}

    public static String getAuthenticatedUser() {
        String username;
        Object authenticatedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (authenticatedUser instanceof UserDetails) {
            username = ((UserDetails) authenticatedUser).getUsername();
        } else {
            username = authenticatedUser.toString();
        }
        return username;
    }
}
