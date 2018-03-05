package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.service.UserService;
import com.sbogdanschi.springboot.service.authentication.SecurityService;
import com.sbogdanschi.springboot.util.PageUrl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

import static com.sbogdanschi.springboot.util.PageUrl.Admin.ADMIN_PAGE;
import static com.sbogdanschi.springboot.util.PageUrl.*;
import static com.sbogdanschi.springboot.util.PageUrl.User.*;

@Controller
public class LoginController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    private final UserService userService;

    private final SecurityService securityService;

    private final AuthenticationManager authenticationManager;

    public LoginController(UserService userService, SecurityService securityService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.securityService = securityService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping(value = INDEX)
    public ModelAndView homePage(ModelAndView modelAndView) {
        LOGGER.debug("Home page");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value = LOGIN)
    public ModelAndView login(ModelAndView modelAndView) {
        LOGGER.debug("Login page");

        if (Optional.empty().equals(getAuthorizedUser())) {
            User user = new User();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("login");
            return modelAndView;
        }

        modelAndView.setViewName(REDIRECT_TO);
        return modelAndView;
    }

    @PostMapping(value = LOGIN)
    public ModelAndView postLogin(@ModelAttribute("user") User user, ModelAndView modelAndView) {
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @PostMapping(value = LOGOUT)
    public ModelAndView postLogout(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        modelAndView.setViewName(REDIRECT + LOGIN);
        return modelAndView;
    }

    @GetMapping(value = REGISTRATION)
    public ModelAndView registration(ModelAndView modelAndView) {
        LOGGER.debug("Registration page");
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = REGISTRATION)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult, ModelAndView modelAndView, HttpServletRequest request,HttpServletResponse response) {
        boolean usernameExists = userService.userExists(user.getUsername());
        boolean emailExists = userService.isEmailRegistered(user.getEmail());

        if (usernameExists) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");
            modelAndView.setViewName(REGISTRATION);
            return modelAndView;
        }

        if (emailExists) {
            bindingResult
                    .rejectValue("email", "error.email",
                            "There is already an user registered with the email provided");
            modelAndView.setViewName(REGISTRATION);
            return modelAndView;
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(REGISTRATION);
            return modelAndView;
        }


        userService.saveUser(user);
//        securityService.autoLogin(user.getUsername(), user.getPassword(), request);
       // authenticateUserAndSetSession(user, request);// TODO:FIX ME
        modelAndView.addObject("successMessage", "User has been registered successfully");
        modelAndView.setViewName(REDIRECT + INDEX);
        LOGGER.debug("User " + user.getUsername() + " was successfully saved");

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_PAGE, method = RequestMethod.GET)
    public ModelAndView adminPage(ModelAndView modelAndView) {


        if (getAuthorizedUser().isPresent()) {
            modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
            modelAndView.setViewName("admin");
            LOGGER.debug("Admin page");
            return modelAndView;
        }

        modelAndView.setViewName(REDIRECT + LOGIN);
        LOGGER.debug("Unauthorized request. Redirecting to login page..");
        return modelAndView;
    }

    @RequestMapping(value = PageUrl.ERROR_PAGE, method = RequestMethod.GET)
    public ModelAndView errorPage(ModelAndView modelAndView) {
        LOGGER.debug("Error page");
        modelAndView.setViewName("error");
        return modelAndView;
    }

    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }

}
