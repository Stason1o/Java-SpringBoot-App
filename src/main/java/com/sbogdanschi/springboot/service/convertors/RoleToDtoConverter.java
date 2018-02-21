package com.sbogdanschi.springboot.service.convertors;

import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.entity.dto.RoleDto;
import org.springframework.core.convert.converter.Converter;

public class RoleToDtoConverter implements Converter<Role, RoleDto> {

    @Override
    public RoleDto convert(Role source) {
        return new RoleDto(source.getId(), source.getRoleType(), source.getActive());
    }

}
