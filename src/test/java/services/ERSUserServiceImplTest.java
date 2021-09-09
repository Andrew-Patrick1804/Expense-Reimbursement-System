package services;

import models.ERSUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ERSUserServiceImplTest {

    ERSUserService ersUserService;
    ERSUser testUser;

    @BeforeEach
    void setup(){
        ersUserService = ERSUserServiceImpl.getInstance();

        testUser = new ERSUser(4, "testEmployee",
                "pass123", "Test",
                "Employee", "testEmployee@email.com",
                1);
    }

    @Test
    void getInstance() {
        assertNotNull(ERSUserServiceImpl.getInstance());
    }

    @Test
    void login() {

        ERSUser retrievedUser = ersUserService.login(testUser);

        assertEquals(testUser, retrievedUser);
    }

    @Test
    void getNullAccount(){

        ERSUser nullUser = new ERSUser();
        nullUser.setUsername("nullUser");

        ERSUser retrievedUser = ersUserService.login(nullUser);

        assertNull(retrievedUser);
    }

    @Test
    void getFirstAndLastName() {

        String expectedName = testUser.getFirst_name() + " " +
                testUser.getLast_name();

        Integer testUserId = testUser.getUser_id();

        String retrievedName = ersUserService.getFirstAndLastName(testUserId);

        assertEquals(expectedName, retrievedName);
    }
}