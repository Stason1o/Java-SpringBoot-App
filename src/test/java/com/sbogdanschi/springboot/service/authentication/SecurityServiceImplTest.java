package com.sbogdanschi.springboot.service.authentication;

import com.sbogdanschi.springboot.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static com.sbogdanschi.springboot.util.TestDataUtil.buildUserForAuthentication;
import static com.sbogdanschi.springboot.util.TestDataUtil.buildValidUser;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityServiceImplTest {

    private ApplicationContext applicationContext;

    private AuthenticationManager authenticationManager;

    @Mock
    private CustomDetailsService customDetailsService;

    private SecurityService securityService;


    @Before
    public void setUp() throws Exception {
        authenticationManager = applicationContext.getBean(AuthenticationManager.class);

        securityService = new SecurityServiceImpl(customDetailsService, authenticationManager);
        applicationContext = new AnnotationConfigApplicationContext(SpringBootApplication.class);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(customDetailsService);
    }

    @Test
    @Ignore //TODO: Fix test
    public void autoLogin() throws Exception {
        User user = buildValidUser();

        UserDetails userDetails = buildUserForAuthentication(user);
        given(customDetailsService.loadUserByUsername(user.getUsername())).willReturn(userDetails);

//        securityService.autoLogin(user.getUsername(), user.getPassword(), re);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());

        verify(customDetailsService).loadUserByUsername(user.getUsername());

    }

}