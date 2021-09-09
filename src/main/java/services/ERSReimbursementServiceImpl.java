package services;

import dao.ERSReimbursementDao;
import dao.ERSReimbursementDaoImpl;
import models.ERSReimbursement;

import java.util.List;

public class ERSReimbursementServiceImpl implements ERSReimbursementService {

    ERSReimbursementDao ersReimbursementDao;

    //singleton design pattern
    private static ERSReimbursementService ersReimbursementService;

    private ERSReimbursementServiceImpl(){
        ersReimbursementDao = ERSReimbursementDaoImpl.getInstance();
    }

    public static ERSReimbursementService getInstance(){
        if (ersReimbursementService == null){
            ersReimbursementService = new ERSReimbursementServiceImpl();
        }
        return ersReimbursementService;
    }

    @Override
    public void addReimbursementRequest(ERSReimbursement ersReimbursement) {
        ersReimbursementDao.addReimbursementRequest(ersReimbursement);
    }

    @Override
    public List<ERSReimbursement> getAllReimbursementRequests() {
        return ersReimbursementDao.getAllReimbursementRequests();
    }

    @Override
    public List<ERSReimbursement> getAllReimbursementRequestsForUser(Integer ersUserId) {
        return ersReimbursementDao.getAllReimbursementRequestsForUser(ersUserId);
    }

    @Override
    public void resolveAndChangeRequestStatus(ERSReimbursement ersReimbursement) {
        ersReimbursementDao.resolveAndChangeRequestStatus(ersReimbursement);
    }

    @Override
    public ERSReimbursement getOneReimbursementRequest(Integer reimbId) {
        return ersReimbursementDao.getOneReimbursementRequest(reimbId);
    }
}
