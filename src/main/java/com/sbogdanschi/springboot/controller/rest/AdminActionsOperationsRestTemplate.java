package com.sbogdanschi.springboot.controller.rest;

import com.sbogdanschi.springboot.entity.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.sbogdanschi.springboot.util.PageUrl.Admin.ADMIN_PAGE;
import static com.sbogdanschi.springboot.util.PageUrl.Admin.USERS_LIST;
import static org.springframework.http.HttpMethod.GET;

public class AdminActionsOperationsRestTemplate implements AdminActionsOperations {

    private final RestTemplate template;

    public AdminActionsOperationsRestTemplate(RestTemplate template) {
        this.template = template;
    }

    @Override
    public ResponseEntity<List<User>> getListOfUsers() {
        return template.exchange(ADMIN_PAGE + USERS_LIST, GET, null, new ParameterizedTypeReference<List<User>>() {});
    }
}
