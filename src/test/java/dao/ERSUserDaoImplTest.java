package dao;

import models.ERSUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ERSUserDaoImplTest {

    ERSUserDao ersUserDao;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        ersUserDao = ERSUserDaoImpl.getInstance();
    }

    @Test
    void getAccount() {

        ERSUser testUser = new ERSUser(1, "user1", "pass123",
                "Han", "Solo", "user1@email.com", 1);

        ERSUser retrievedUser = ersUserDao.getAccount("user1");

        assertTrue(retrievedUser.equals(testUser));

    }

    @Test
    void getNullAccount(){
        ERSUser nullTest = ersUserDao.getAccount("nullAccount");

        assertNull(nullTest);
    }
}