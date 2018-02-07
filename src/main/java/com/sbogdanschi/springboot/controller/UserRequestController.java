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
public class UserRequestController extends BaseController {

    private final UserService userService;

    public UserRequestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public ModelAndView userList(ModelAndView modelAndView) {
        List<User> users = userService.retrieveAllUsers();
        modelAndView.addObject("userList", users);
        modelAndView.setViewName("adminpanel");
        return modelAndView;
    }

    @RequestMapping(value = "/testrest", method = RequestMethod.GET)
    public ModelAndView restUser(ModelAndView modelAndView) {
//        ModelAndView modelAndView = new ModelAndView();
//        List<User> users = userService.retrieveAllUsers();
//        modelAndView.addObject("userList", users);
        modelAndView.setViewName("testrest");
        return modelAndView;
    }

    @RequestMapping(value = "/user-management", method = RequestMethod.GET)
    public ModelAndView getIndexPage(ModelAndView modelAndView) {
        modelAndView.setViewName("user-management");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/search", method = RequestMethod.GET)
    public ModelAndView getSearchPage(ModelAndView modelAndView) {
        modelAndView.setViewName("search");
        return modelAndView;
    }
}
