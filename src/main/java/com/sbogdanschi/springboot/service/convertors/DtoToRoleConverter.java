package com.sbogdanschi.springboot.service.convertors;

import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.entity.dto.RoleDto;
import org.springframework.core.convert.converter.Converter;

public class DtoToRoleConverter implements Converter<RoleDto, Role> {

    @Override
    public Role convert(RoleDto source) {
        return new Role(source.getId(), source.getRoleType(), source.getActive());
    }
}
