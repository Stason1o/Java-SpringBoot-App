package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.Greeting;
import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.sbogdanschi.springboot.util.ControllerUtils.getAuthenticatedUser;

@RestController
public class RESTController {

    private static final Logger LOGGER = LogManager.getLogger(RESTController.class);

    private final UserService userService;

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    public RESTController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(TEMPLATE, name));
    }

    @RequestMapping(value = "/users/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.retrieveAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/loggedin/", method = RequestMethod.GET)
    @ResponseBody
    public User getUser () {
        LOGGER.error(getAuthenticatedUser());
        User user = userService.findByUsername(getAuthenticatedUser());
        LOGGER.error(user);
        return user;
    }

}
