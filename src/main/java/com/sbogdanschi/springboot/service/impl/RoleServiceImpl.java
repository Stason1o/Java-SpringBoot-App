package com.sbogdanschi.springboot.service.impl;

import com.sbogdanschi.springboot.dao.RoleRepository;
import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.entity.dto.RoleDto;
import com.sbogdanschi.springboot.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LogManager.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    private final ConversionService conversionService;

    public RoleServiceImpl(RoleRepository roleRepository, ConversionService conversionService) {
        this.roleRepository = roleRepository;
        this.conversionService = conversionService;
    }

    //TODO: remove if not needed
    @Override
    public Map<Long, List<RoleDto>> convertAndIdentifyUserRoles(List<User> sourceList) {
        Map<Long, List<RoleDto>> roleMap = new HashMap<>();
        List<Role> roles = roleRepository.findAll();


        sourceList.forEach(user -> {
            List<Role> roleList = new ArrayList<>(user.getRoles());

            roles.forEach(role -> {
                if (roleList.contains(role)) {
                    roleList.get(roleList.indexOf(role)).setActive( true);
                } else {
                    role.setActive(false);
                    roleList.add(role);
                }
            });
            user.setRoles(new HashSet<>(roleList));
        });

        LOGGER.warn("sourceList {}", sourceList);
        sourceList.forEach(user ->
                roleMap.put(user.getId(),
                        user.getRoles().stream().map(role ->
                                conversionService.convert(role, RoleDto.class))
                                .collect(Collectors.toList())));

        LOGGER.debug("Retrieved map: {}", roleMap);
        return roleMap;
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role getRoleByType(String type) {
        return roleRepository.findByRoleType(type);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
