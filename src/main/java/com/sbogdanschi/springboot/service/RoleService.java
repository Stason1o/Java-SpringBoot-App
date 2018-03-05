package com.sbogdanschi.springboot.service;

import com.sbogdanschi.springboot.entity.Role;

import java.util.List;

public interface RoleService {

    Role getRoleById(Integer id);

    Role getRoleByType(String type);

    List<Role> findAll();

}