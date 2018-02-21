package com.sbogdanschi.springboot.service.authentication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service("securityService") //TODO: FIX ME
public class SecurityServiceImpl implements SecurityService {

    private static final Logger LOGGER = LogManager.getLogger(SecurityServiceImpl.class);

    private final CustomDetailsService detailsService;

    private final AuthenticationManager authenticationManager;

    public SecurityServiceImpl(CustomDetailsService detailsService, AuthenticationManager authenticationManager) {
        this.detailsService = detailsService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void autoLogin(String username, String password, HttpServletRequest request) {
        UserDetails userDetails = detailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        LOGGER.debug("AuthenticationToken: credentials: {}  principal: {}", token.getCredentials(), token.getPrincipal());
        authenticationManager.authenticate(token);

        if(token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
        }
    }
}
