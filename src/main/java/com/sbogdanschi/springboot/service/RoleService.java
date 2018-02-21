package com.sbogdanschi.springboot.service;

import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.entity.dto.RoleDto;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Map<Long, List<RoleDto>> convertAndIdentifyUserRoles(List<User> users);

    Role getRoleById(Integer id);

    Role getRoleByType(String type);

    List<Role> findAll();

}