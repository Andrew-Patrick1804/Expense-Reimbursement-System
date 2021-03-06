package services;

import models.ERSReimbursement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ERSReimbursementServiceImplTest {

    ERSReimbursementService ersReimbursementService;
    ERSReimbursement testReimbursement;

    @BeforeEach
    void setUp(){
        ersReimbursementService = ERSReimbursementServiceImpl.getInstance();

        testReimbursement = new ERSReimbursement();
        testReimbursement.setReimb_amount(111.11f);
        testReimbursement.setReimb_description("GENERATED BY A UNIT TEST");
        testReimbursement.setReimb_author_id(4); //from the testUser in ERSUserServiceImplTest.java
        testReimbursement.setReimb_type_id(4);
    }

    @Test
    void getInstance() {
        assertNotNull(ersReimbursementService);
    }

    @Test
    void addReimbursementRequest() {
        ersReimbursementService.addReimbursementRequest(testReimbursement);

        ERSReimbursement retrievedReimb;

        // not sure how to get a reimbursement ID from here...
        /*retrievedReimb = ersReimbursementService.getOneReimbursementRequest(testReimbursement.getReimb_id());

        assertEquals(retrievedReimb.getReimb_amount(), testReimbursement.getReimb_amount());
        assertEquals(retrievedReimb.getReimb_description(), testReimbursement.getReimb_description());
        assertEquals(retrievedReimb.getReimb_author_id(), testReimbursement.getReimb_author_id());
        assertEquals(retrievedReimb.getReimb_type_id(), testReimbursement.getReimb_type_id());*/
    }

    @Test
    void getAllReimbursementRequests() {
        List<ERSReimbursement> ersReimbursementList;
        ersReimbursementList = ersReimbursementService.getAllReimbursementRequests();

        assertNotNull(ersReimbursementList);
        assertTrue(ersReimbursementList.size() > 0);
    }

    @Test
    void getAllReimbursementRequestsForUser() {
        List<ERSReimbursement> ersReimbursementList;
        ersReimbursementList = ersReimbursementService.getAllReimbursementRequestsForUser(4); //from the testUser

        assertNotNull(ersReimbursementList);
        assertTrue(ersReimbursementList.size() > 0);
    }

    @Test
    void resolveAndChangeRequestStatus() {
        ERSReimbursement resolvedReimb = new ERSReimbursement();
        resolvedReimb.setReimb_id(9);
        resolvedReimb.setReimb_type_id(3);
        resolvedReimb.setReimb_resolver_id(5);
    }

    @Test
    void getOneReimbursementRequest() {
        ERSReimbursement ersReimbursement;
        ersReimbursement = ersReimbursementService.getOneReimbursementRequest(9);

        assertEquals(testReimbursement.getReimb_amount(), ersReimbursement.getReimb_amount());

    }
}