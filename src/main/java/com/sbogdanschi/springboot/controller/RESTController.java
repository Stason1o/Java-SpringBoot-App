package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/user-management/")
public class RESTController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(RESTController.class);

    private final UserService userService;

    public RESTController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "loggedin/", method = RequestMethod.GET)
    public User getUser() {
        LOGGER.debug(getAuthorizedUser().get());
        User user = new User();
        if (getAuthorizedUser().isPresent()) {
            user = userService.findByUsername(getAuthorizedUser().get());
        }
        LOGGER.debug(user);
        return user;
    }


}
