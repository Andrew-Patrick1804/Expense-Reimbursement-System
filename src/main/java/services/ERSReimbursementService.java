package services;

import models.ERSReimbursement;

import java.util.List;

public interface ERSReimbursementService {
    void addReimbursementRequest(ERSReimbursement ersReimbursement);
    List<ERSReimbursement> getAllReimbursementRequests();
    List<ERSReimbursement> getAllReimbursementRequestsForUser(Integer ersUserId);
    void resolveAndChangeRequestStatus(ERSReimbursement ersReimbursement);
    ERSReimbursement getOneReimbursementRequest(Integer reimbId);
}
