package dao;

import models.ERSReimbursement;
import models.ERSUser;

import java.util.List;

public interface ERSReimbursementDao {

    void addReimbursementRequest(ERSReimbursement ersReimbursement);
    List<ERSReimbursement> getAllReimbursementRequests();
    List<ERSReimbursement> getAllReimbursementRequestsForUser(Integer ersUserId);
    void resolveAndChangeRequestStatus(ERSReimbursement ersReimbursement);
    ERSReimbursement getOneReimbursementRequest(Integer reimbId);
}
