package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.service.UserService;
import com.sbogdanschi.springboot.util.PageUrl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.util.Optional;

import static com.sbogdanschi.springboot.util.PageUrl.Admin.ADMIN_PAGE;
import static com.sbogdanschi.springboot.util.PageUrl.INDEX;
import static com.sbogdanschi.springboot.util.PageUrl.REDIRECT;
import static com.sbogdanschi.springboot.util.PageUrl.REDIRECT_TO;
import static com.sbogdanschi.springboot.util.PageUrl.User.LOGIN;
import static com.sbogdanschi.springboot.util.PageUrl.User.LOGOUT;
import static com.sbogdanschi.springboot.util.PageUrl.User.REGISTRATION;

@Controller
public class LoginController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    private final UserService userService;

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
            modelAndView.setViewName(REGISTRATION);
            return modelAndView;
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(REGISTRATION);
            return modelAndView;
        }

        userService.saveUser(user);
        modelAndView.addObject("successMessage", "User has been registered successfully");
        modelAndView.setViewName(REDIRECT + LOGIN);
        LOGGER.debug("User " + user.getUsername() + " was successfully saved");

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_PAGE, method = RequestMethod.GET)
    public ModelAndView adminPage(ModelAndView modelAndView) {
        LOGGER.debug("Admin page");
        User user = new User();
        if (getAuthorizedUser().isPresent()) {
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
