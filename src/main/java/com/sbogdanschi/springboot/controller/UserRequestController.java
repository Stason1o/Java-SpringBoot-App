package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserRequestController {

    private final UserService userService;

    @Autowired
    public UserRequestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public ModelAndView userList() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.retrieveAllUsers();
        modelAndView.addObject("userList", users);
        modelAndView.setViewName("adminpanel");
        return modelAndView;
    }

    @RequestMapping(value = "/testrest", method = RequestMethod.GET)
    public ModelAndView restUser() {
        ModelAndView modelAndView = new ModelAndView();
//        List<User> users = userService.retrieveAllUsers();
//        modelAndView.addObject("userList", users);
        modelAndView.setViewName("testrest");
        return modelAndView;
    }
}
