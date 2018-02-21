package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.sbogdanschi.springboot.util.PageUrl.Admin.*;

@Controller
@RequestMapping(ADMIN_PAGE)
public class AdminActionsController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(AdminActionsController.class);

    private final AdminActionsApi adminService;

    private final RoleService roleService;

    public AdminActionsController(AdminActionsApi adminService, RoleService roleService) {
        this.adminService = adminService;
        this.roleService = roleService;
    }

    @GetMapping(value = USER_MANAGEMENT + USERS)
    public ModelAndView getUserListPage(ModelAndView modelAndView) {
        ResponseEntity<List<User>> responseEntity = adminService.getListOfUsers();

        List<User> users = responseEntity.getBody();
        List<Role> roles = roleService.findAll();

        modelAndView.addObject("userList", users);
        modelAndView.addObject("roleList", roles);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @GetMapping(value = USER_MANAGEMENT)
    public ModelAndView getUserManagementPage(ModelAndView modelAndView) {
        modelAndView.setViewName("user-management");
        return modelAndView;
    }

    @GetMapping(value = SEARCH)
    public ModelAndView getSearchPage(ModelAndView modelAndView) {
        modelAndView.setViewName("search");
        return modelAndView;
    }
}
