package com.sbogdanschi.springboot.dao;

import com.sbogdanschi.springboot.entity.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static com.sbogdanschi.springboot.util.TestDataUtil.buildUserWithoutRoles;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE) //indicates which DB to use (e.g. embedded, real, etc.)
@Rollback
public class UserRepositoryTest { //TODO: FIX ME

    private User user;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        entityManager.getEntityManager().createNativeQuery("SET DATABASE REFERENTIAL INTEGRITY FALSE");
        user = buildUserWithoutRoles("Qwerty");
        entityManager.persist(user);
        entityManager.flush();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.getEntityManager().createNativeQuery("SET DATABASE REFERENTIAL INTEGRITY TRUE");
    }

    @Test
    public void findByUsernameOrEmail() throws Exception {
    }

    @Test
    public void userExists() throws Exception {
        boolean exists = userRepository.isUsernameRegistered(user.getUsername());

        Assert.assertTrue(exists);
    }

    @Test //TODO: FIX ME
    @Ignore
    public void updateUser() throws Exception {
        System.out.println("================" + this.user);
        String username = "NewUsername";
        String email = "newEmail@domain.com";
        String firstName = "newFirstName";
        String lastName = "newLastName";

//        User expectedUser = userRepository.findByUsername(this.user.getUsername());

        userRepository.updateUserById(this.user.getId(), username, email, firstName, lastName);

        User actualUser = userRepository.findById(this.user.getId());
        System.out.println(actualUser);

        assertNotNull(actualUser);

        assertEquals(username, actualUser.getUsername());
        assertEquals(email, actualUser.getEmail());
        assertEquals(firstName, actualUser.getFirstName());
        assertEquals(lastName, actualUser.getLastName());

    }

    @Test
    @Ignore
    public void deleteAllUsers() throws Exception {
    }

}