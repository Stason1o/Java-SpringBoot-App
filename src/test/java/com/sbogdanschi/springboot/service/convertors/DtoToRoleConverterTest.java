package com.sbogdanschi.springboot.service.convertors;

import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.entity.dto.RoleDto;
import org.junit.Before;
import org.junit.Test;

import static com.sbogdanschi.springboot.util.TestDataUtil.buildRoleDto;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DtoToRoleConverterTest {

    private DtoToRoleConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new DtoToRoleConverter();
    }

    @Test
    public void convert() throws Exception {
        RoleDto roleDto = buildRoleDto(1, "USER");

        Role role = converter.convert(roleDto);

        assertNotNull(roleDto);
        assertEquals(roleDto.getId(), role.getId());
        assertEquals(roleDto.getRoleType(), role.getRoleType());
    }

}