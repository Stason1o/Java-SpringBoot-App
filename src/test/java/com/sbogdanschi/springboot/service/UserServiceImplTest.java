package com.sbogdanschi.springboot.service;

import com.sbogdanschi.springboot.dao.RoleRepository;
import com.sbogdanschi.springboot.dao.UserRepository;
import com.sbogdanschi.springboot.entity.User;
import com.sbogdanschi.springboot.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by sbogdanschi on 11/8/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    private UserService userService;

    private User user;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl(userRepository, roleRepository, encoder);

        user = new User();
        user.setEmail("email@domain.com");
        user.setUsername("dummyUsername");
        user.setActive(true);
        user.setFirstName("Dummy");
        user.setLastName("Dummy last name");
        user.setId(1L);
        user.setPassword("dummypassword");
        user.setRoles(new HashSet<>());
    }

    @After
    public void tearDown() throws Exception {
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testFindUserByEmail() throws Exception {
        assertThat(mockingDetails(userRepository).isMock(), is(true));

        given(userRepository.findByEmail(user.getEmail())).willReturn(user);

        User actualUser = userService.findUserByEmail(user.getEmail());

        assertNotNull(actualUser);
        assertEquals(user, actualUser);

        Mockito.verify(userRepository).findByEmail(user.getEmail());
    }

    @Test
    public void testFindByUsername() throws Exception {
        assertThat(mockingDetails(userRepository).isMock(), is(true));

        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        User actualUser = userService.findByUsername(user.getUsername());

        assertNotNull(actualUser);
        assertEquals(user, actualUser);

        verify(userRepository).findByUsername(user.getUsername());
    }

    @Test
    public void testSaveUser() throws Exception {

    }

}