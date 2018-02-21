package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.service.UserService;
import com.sbogdanschi.springboot.service.authentication.SecurityService;
import com.sbogdanschi.springboot.util.TestDataUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.View;

import static com.sbogdanschi.springboot.util.PageUrl.Admin.ADMIN_PAGE;
import static com.sbogdanschi.springboot.util.PageUrl.INDEX;
import static com.sbogdanschi.springboot.util.PageUrl.REDIRECT;
import static com.sbogdanschi.springboot.util.PageUrl.User.LOGIN;
import static com.sbogdanschi.springboot.util.PageUrl.User.REGISTRATION;
import static com.sbogdanschi.springboot.util.SecurityContextInitializationHelper.getAuthentication;
import static com.sbogdanschi.springboot.util.TestDataUtil.buildValidUser;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by sbogdanschi on 11/13/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private View mockView;

    @Mock
    private SecurityService securityService;

    @InjectMocks
    private LoginController loginController;

    private MockMvc mockMvc;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = TestDataUtil.buildUser();
//        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(loginController).setSingleView(mockView).build();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void homePage() throws Exception {
        mockMvc.perform(get(INDEX))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void login() throws Exception {
        mockMvc.perform(get(LOGIN))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void postLogin() throws Exception {
        user = buildValidUser();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(LOGIN)
                .param("username", user.getUsername())
                .param("password", user.getPassword());

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    public void testGetRegistration() throws Exception {
        user = new User();
        mockMvc.perform(get(REGISTRATION))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", user))
                .andExpect(view().name("registration"));
    }

    @Test
    public void testPostRegistration() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(REGISTRATION)
                .param("firstName", "FirstName")
                .param("lastName", "LastName")
                .param("email", "email@domain.com")
                .param("username", "Username")
                .param("password", "password")
                .param("repeatPassword", "password");

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(view().name(REDIRECT + INDEX));
    }

    @Test
    public void testPostRegistrationWithInvalidFields() throws Exception {
        user = TestDataUtil.buildUser();
        doNothing().when(userService).saveUser(user);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(REGISTRATION)
                .param("firstName", "")
                .param("lastName", "")
                .param("email", "email")
                .param("username", "usnm")
                .param("password", "pssw")
                .param("repeatPassword", "psswrd");

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("user"))
                .andExpect(view().name(REGISTRATION));
    }

    @Test
    public void testGetAdminPageWithoutAuthentication() throws Exception {
        mockMvc.perform(get(ADMIN_PAGE))
                .andExpect(status().isOk())
                .andExpect(view().name(REDIRECT + LOGIN));
    }

    @Test
    public void testGetAdminPageWithAuthentication() throws Exception {
        this.user = buildValidUser();
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) getAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(token);

        mockMvc.perform(get(ADMIN_PAGE))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"));
    }

    @Test
    public void errorPage() throws Exception {
        mockMvc.perform(get("/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

}