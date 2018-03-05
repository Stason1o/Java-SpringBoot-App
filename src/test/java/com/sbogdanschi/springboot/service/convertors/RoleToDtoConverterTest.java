package com.sbogdanschi.springboot.service.convertors;

import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.entity.dto.RoleDto;
import org.junit.Before;
import org.junit.Test;

import static com.sbogdanschi.springboot.util.TestDataUtil.buildRole;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RoleToDtoConverterTest {

    private RoleToDtoConverter converter;

    @Before
    public void setUp() {
        converter = new RoleToDtoConverter();
    }

    @Test
    public void convert() throws Exception {
        Role role = buildRole(1, "USER");

        RoleDto roleDto = converter.convert(role);

        assertNotNull(roleDto);
        assertEquals(role.getId(), roleDto.getId());
        assertEquals(role.getRoleType(), roleDto.getRoleType());
    }

}