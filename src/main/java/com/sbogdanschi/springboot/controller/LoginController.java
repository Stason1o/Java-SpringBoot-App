package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.service.UserService;
import com.sbogdanschi.springboot.util.PageUrl;
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
import java.util.Optional;

import static com.sbogdanschi.springboot.util.ControllerUtils.getAuthorizedUser;
import static com.sbogdanschi.springboot.util.PageUrl.Admin.ADMIN;
import static com.sbogdanschi.springboot.util.PageUrl.INDEX;
import static com.sbogdanschi.springboot.util.PageUrl.REDIRECT;
import static com.sbogdanschi.springboot.util.PageUrl.REDIRECT_TO;
import static com.sbogdanschi.springboot.util.PageUrl.User.LOGIN;
import static com.sbogdanschi.springboot.util.PageUrl.User.REGISTRATION;

@Controller
public class LoginController extends BaseController{

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
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

        if(Optional.empty().equals(getAuthorizedUser())) {
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

    @RequestMapping(value = REGISTRATION, method = RequestMethod.GET)
    public ModelAndView registration(ModelAndView modelAndView) {
        LOGGER.debug("Registration page");
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = REGISTRATION, method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult, ModelAndView modelAndView) {
        User userExists = userService.findByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(REDIRECT + REGISTRATION);
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.setViewName(REDIRECT + LOGIN);
            LOGGER.debug("User " + user.getUsername() + " was successfully saved");
        }
        return modelAndView;
    }

    @RequestMapping(value = ADMIN, method = RequestMethod.GET)
    public ModelAndView home(ModelAndView modelAndView) {
        LOGGER.debug("Admin page");
        User user = new User();
        if(getAuthorizedUser().isPresent()) {
            user = userService.findByUsername(getAuthorizedUser().get());
        }

        modelAndView.addObject("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping(value = PageUrl.ERROR_PAGE, method = RequestMethod.GET)
    public ModelAndView errorPage(ModelAndView modelAndView) {
        LOGGER.debug("Error page");
        modelAndView.setViewName("error");
        return modelAndView;
    }


}
