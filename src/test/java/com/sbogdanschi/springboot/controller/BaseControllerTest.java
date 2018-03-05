package com.sbogdanschi.springboot.controller;

import com.sbogdanschi.springboot.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static com.sbogdanschi.springboot.util.SecurityContextInitializationHelper.getAuthentication;
import static com.sbogdanschi.springboot.util.TestDataUtil.buildValidUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BaseControllerTest {

    private BaseController baseController;

    @Before
    public void setUp() {
        baseController = new BaseController();
    }

    @Test
    public void testGetAuthorizedUserWithAuthentication() throws Exception {
        User user = buildValidUser();
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(user));

        Optional<String> authorizedUser = baseController.getAuthorizedUser();

        assertTrue(authorizedUser.isPresent());
        assertEquals(authorizedUser.get(), user.getUsername());
    }

    @Test
    public void testGetAuthorizedUserWithoutAuthentication() throws Exception {

        Optional<String> authorizedUser = baseController.getAuthorizedUser();

        assertFalse(authorizedUser.isPresent());
        assertEquals(Optional.empty(), authorizedUser);
    }
}