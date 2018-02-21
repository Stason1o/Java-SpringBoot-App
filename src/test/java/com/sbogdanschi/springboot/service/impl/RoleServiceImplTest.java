//package com.sbogdanschi.springboot.service.impl;
//
//import com.sbogdanschi.springboot.dao.RoleRepository;
//import com.sbogdanschi.springboot.entity.Role;
//import com.sbogdanschi.springboot.entity.User;
//import com.sbogdanschi.springboot.service.RoleService;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.core.convert.ConversionService;
//import org.springframework.core.convert.support.DefaultConversionService;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//
//@RunWith(MockitoJUnitRunner.class)
//public class RoleServiceImplTest {
//
//    @Mock
//    private RoleRepository roleRepository;
//
//    private ConversionService conversionService;
//
//    private RoleService roleService;
//
//    @Before
//    public void setUp() {
//        conversionService = new DefaultConversionService();
//        roleService = new RoleServiceImpl(roleRepository, conversionService);
//    }
//
//    @After
//    public void tearDown() {
//        verifyNoMoreInteractions(roleRepository);
//    }
//
////    @Test
////    public void convertAndIdentifyUserRoles() throws Exception {
////        Assertions.assertThat(roleService).isNotNull();
////        List<User> users = buildUserList();
////
////        when(roleRepository.findAll()).thenReturn(buildRoleList());
////
////        roleService.convertAndIdentifyUserRoles(users);
////
////        verify(roleRepository).findAll();
////
////    }
//
//    private List<User> buildUserList() {
//        List<User> users = new ArrayList<>();
//        users.add(buildUser(1L));
//        users.add(buildUser(2L));
//        users.add(buildUser(3L));
//        return users;
//    }
//
//    private User buildUser(Long id) {
//        return User.builder().id(id)
//                .email("dummy@mail.ru")
//                .firstName("DummyName")
//                .lastName("DummyLastName")
//                .active(false)
//                .username("dummyUsername").roles(new HashSet<>(buildRoleList())).build();
//    }
//
//    private Role buildRole(String roleType) {
//        return Role.builder().id(1).roleType(roleType).active(false).build();
//    }
//
//    private List<Role> buildRoleList() {
//        List<Role> roles = new ArrayList<>();
//        roles.add(buildRole("ADMIN"));
//        roles.add(buildRole("USER"));
//        return roles;
//    }
//}