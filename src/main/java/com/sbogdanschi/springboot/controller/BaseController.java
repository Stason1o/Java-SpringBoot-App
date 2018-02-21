package com.sbogdanschi.springboot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public abstract class BaseController {

    private static final Logger LOGGER = LogManager.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(final HttpServletRequest request, final Exception exception, ModelAndView view) {
        LOGGER.error("Request Error call.", exception);
        view.setViewName("error");
        return view;
    }

    public Optional<String> getAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return Optional.of(authentication.getName());
        }

        return Optional.empty();
    }

}