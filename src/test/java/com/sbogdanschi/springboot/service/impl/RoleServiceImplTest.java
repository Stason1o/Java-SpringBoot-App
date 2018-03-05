package com.sbogdanschi.springboot.service.impl;

import com.sbogdanschi.springboot.dao.RoleRepository;
import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.service.RoleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.sbogdanschi.springboot.util.TestDataUtil.buildRole;
import static com.sbogdanschi.springboot.util.TestDataUtil.buildRoleList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImplTest {

    private static final String ADMIN = "ADMIN";

    private static final String USER = "USER";

    @Mock
    private RoleRepository roleRepository;

    private RoleService roleService;

    @Before
    public void setUp() {
        roleService = new RoleServiceImpl(roleRepository);
        assertThat(mockingDetails(roleRepository).isMock(), is(true));
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(roleRepository);
    }

    @Test
    public void testGetRoleById() {
        Role role = buildRole(1, ADMIN);

        given(roleRepository.findById(1)).willReturn(role);

        Role actualRole = roleService.getRoleById(1);

        assertNotNull(actualRole);
        assertEquals(actualRole, role);

        verify(roleRepository).findById(1);
    }

    @Test
    public void testGetRoleByType() {
        Role role = buildRole(2, USER);

        given(roleRepository.findByRoleType(role.getRoleType())).willReturn(role);

        Role actualRole = roleService.getRoleByType(USER);

        assertNotNull(actualRole);
        assertEquals(actualRole, role);

        verify(roleRepository).findByRoleType(USER);
    }

    @Test
    public void testFindAll() {
        Set<Role> roles = buildRoleList();

        given(roleRepository.findAll()).willReturn(new ArrayList<>(roles));

        List<Role> actualRoles = roleRepository.findAll();

        assertNotNull(actualRoles);

        actualRoles.forEach(role -> assertTrue(roles.contains(role)));

        verify(roleRepository).findAll();
    }


}