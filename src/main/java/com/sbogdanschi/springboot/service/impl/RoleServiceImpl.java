package com.sbogdanschi.springboot.service.impl;

import com.sbogdanschi.springboot.dao.RoleRepository;
import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LogManager.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Integer id) {
        Role role = roleRepository.findById(id);
        LOGGER.debug("Role with id: {} was retrieved", role.getId());
        return role;
    }

    @Override
    public Role getRoleByType(String type) {
        Role role = roleRepository.findByRoleType(type);
        LOGGER.debug("Role with type: {} was retrieved", role.getRoleType());
        return role;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = roleRepository.findAll();
        LOGGER.debug("Retrieved role list : {}", roles);
        return roles;
    }
}
