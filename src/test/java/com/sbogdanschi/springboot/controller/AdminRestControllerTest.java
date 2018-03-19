package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.entity.model.SearchCriteria;
import com.sbogdanschi.springboot.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.util.List;

import static com.sbogdanschi.springboot.util.PageUrl.Admin.ADMIN_PAGE;
import static com.sbogdanschi.springboot.util.PageUrl.Admin.SEARCH;
import static com.sbogdanschi.springboot.util.SecurityContextInitializationHelper.getAuthentication;
import static com.sbogdanschi.springboot.util.TestDataUtil.buildUserList;
import static com.sbogdanschi.springboot.util.TestDataUtil.buildValidUser;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
public class AdminRestControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private View mockView;

    @InjectMocks
    private AdminRestController adminRestController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(adminRestController).setSingleView(mockView).build();
        User user = buildValidUser();
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) getAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    @Test
    @Ignore //TODO: FIX ME
    public void getSearchResultViaAjax() throws Exception {
        SearchCriteria searchCriteria = new SearchCriteria("Username");
        List<User> users = buildUserList();

        when(userService.findByUsernameOrEmail(searchCriteria.getUsername())).thenReturn(users);

        mockMvc.perform(post(ADMIN_PAGE + SEARCH))
                .andExpect(content().string(contains("success")))
                .andExpect(status().isOk());
    }
}