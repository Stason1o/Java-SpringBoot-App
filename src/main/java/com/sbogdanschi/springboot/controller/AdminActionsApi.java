package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminActionsApi {

    ResponseEntity<List<User>> getListOfUsers();
}
