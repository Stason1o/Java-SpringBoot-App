package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.Role;
import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.service.RoleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.sbogdanschi.springboot.util.PageUrl.Admin.*;
import static com.sbogdanschi.springboot.util.SecurityContextInitializationHelper.getAuthentication;
import static com.sbogdanschi.springboot.util.TestDataUtil.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
public class AdminActionsControllerTest {

    @Mock
    private RoleService roleService;

    @Mock
    private AdminActionsApi adminService;

    @Mock
    private View mockView;

    @InjectMocks
    private AdminActionsController adminActionsController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(adminActionsController).setSingleView(mockView).build();
        User user = buildValidUser();
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) getAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    @Test
    public void testGetUserListPage() throws Exception {
        List<User> users = buildUserList();
        ResponseEntity<List<User>> responseEntity = new ResponseEntity<List<User>>(users, HttpStatus.OK);
        Set<Role> roles = buildRoleList();
        when(roleService.findAll()).thenReturn(new ArrayList<>(roles));
        when(adminService.getListOfUsers()).thenReturn(responseEntity);

        mockMvc.perform(get(ADMIN_PAGE + USER_MANAGEMENT + USERS))
                .andExpect(status().isOk())
                .andExpect(view().name("user-list"));

        verify(roleService).findAll();
        verify(adminService, times(1)).getListOfUsers();

        verifyNoMoreInteractions(roleService, adminService);
    }

    @Test
    public void testGetUserManagementPage() throws Exception {
        mockMvc.perform(get(ADMIN_PAGE + USER_MANAGEMENT))
                .andExpect(status().isOk())
                .andExpect(view().name("user-management"));
    }

    @Test
    public void testGetSearchPage() throws Exception {
        mockMvc.perform(get(ADMIN_PAGE + SEARCH))
                .andExpect(status().isOk())
                .andExpect(view().name("search"));
    }

}