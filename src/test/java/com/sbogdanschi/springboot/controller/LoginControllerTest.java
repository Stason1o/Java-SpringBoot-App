package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.service.UserService;
import com.sbogdanschi.springboot.util.testutil.TestDataUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by sbogdanschi on 11/13/2017.
 */
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private UserService userService;

    @Mock
    private View mockView;

    private MockMvc mockMvc;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = TestDataUtil.buildUser();
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(loginController).setSingleView(mockView).build();

        doNothing().when(userService).saveUser(user);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void homePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void login() throws Exception {

    }

    @Test
    public void postLogin() throws Exception {
    }

    @Test
    public void registration() throws Exception {
        user = new User();
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", user))
                .andExpect(view().name("registration"));
    }

//    @Test
//    public void createNewUser() throws Exception {
//
//        user = TestDataUtil.buildUser();
//        mockMvc.perform(post("/registration"))
//                .andExpect(status().isOk())
//                .andExpect(status().isCreated());
//    }

//    @Test
//    public void home() throws Exception {
//        mockMvc.perform(get("/admin"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("admin"));
//    }

    @Test
    public void errorPage() throws Exception {
        mockMvc.perform(get("/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

}