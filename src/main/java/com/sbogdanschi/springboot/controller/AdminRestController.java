package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.model.RetrievedUsersResponse;
import com.sbogdanschi.springboot.model.SearchCriteria;
import com.sbogdanschi.springboot.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.sbogdanschi.springboot.util.PageUrl.Admin.ADMIN_PAGE;
import static com.sbogdanschi.springboot.util.PageUrl.Admin.SEARCH;

@Controller
@RequestMapping(ADMIN_PAGE)
public class AdminRestController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(AdminRestController.class);

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(SEARCH)
    public ResponseEntity getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors) {
        LOGGER.warn("getSearchResultViaAjax");
        RetrievedUsersResponse result = new RetrievedUsersResponse();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            LOGGER.warn("ERROR HAPPENED WHILE RETRIEVING.");
            result.setMsg(errors.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);

        }

        List<User> users = userService.findByUsernameOrEmail(search.getUsername());
        LOGGER.warn("List of retrieved users: {} ", users);
        if (users.isEmpty()) {
            result.setMsg("no user found!");
        } else {
            result.setMsg("success");
        }
        result.setRetrievedUsers(users);

        return ResponseEntity.ok(result);

    }
}
