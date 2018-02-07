package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping("/admin/user-management/")
public class RESTController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(RESTController.class);

    private final UserService userService;

    private static final String TEMPLATE = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    public RESTController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "users/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers1() {
        List<User> users = userService.retrieveAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "loggedin/", method = RequestMethod.GET)
    @ResponseBody
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
