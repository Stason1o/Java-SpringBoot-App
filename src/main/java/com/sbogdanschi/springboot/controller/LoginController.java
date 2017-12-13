package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public ModelAndView homePage() {
        LOGGER.debug("Home page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greeting");
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView login() {
        LOGGER.debug("Login page");
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        Integer i = 10;
        modelAndView.addObject("user", user);
        modelAndView.addObject("number", i);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping(value = "/login")
    public ModelAndView postLogin(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        LOGGER.debug("Registration page");
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("redirect:/registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.setViewName("redirect:/login");
            LOGGER.debug("User " + user.getUsername() + " was successfully saved");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView home() {
        LOGGER.debug("Admin page");
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView errorPage() {
        LOGGER.debug("Error page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }


}
