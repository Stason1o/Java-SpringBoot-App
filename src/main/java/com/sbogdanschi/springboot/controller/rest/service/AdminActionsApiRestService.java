package com.sbogdanschi.springboot.controller.rest.service;

import com.sbogdanschi.springboot.controller.AdminActionsApi;
import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.sbogdanschi.springboot.util.PageUrl.Admin.ADMIN_PAGE;
import static com.sbogdanschi.springboot.util.PageUrl.Admin.USERS_LIST;
import static com.sbogdanschi.springboot.util.PageUrl.Common.APPLICATION_JSON;

@RestController
@Api(value = "Admin Actions Service",
        produces = APPLICATION_JSON,
        consumes = APPLICATION_JSON)
@RequestMapping(ADMIN_PAGE)
public class AdminActionsApiRestService implements AdminActionsApi {

    private final UserService userService;

    public AdminActionsApiRestService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping(USERS_LIST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 500, message = "Something wrong with the server")
    })
    public ResponseEntity<List<User>> getListOfUsers() {
        List<User> users = userService.retrieveAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
