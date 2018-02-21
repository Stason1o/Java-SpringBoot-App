package com.sbogdanschi.springboot.controller.rest;

import com.sbogdanschi.springboot.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminActionsOperations {

    ResponseEntity<List<User>> getListOfUsers();

}
