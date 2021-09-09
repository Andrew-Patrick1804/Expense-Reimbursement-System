package dao;

import models.ERSReimbursement;
import models.ERSUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dao.DatabaseInfo.*;

public class ERSReimbursementDaoImpl implements ERSReimbursementDao{

    //singleton pattern
    private static ERSReimbursementDao ersReimbursementDao;

    private ERSReimbursementDaoImpl(){
        //tomcat needs this
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static ERSReimbursementDao getInstance(){
        if(ersReimbursementDao == null){
            ersReimbursementDao = new ERSReimbursementDaoImpl();
        }
        return ersReimbursementDao;
    }


    @Override
    public void addReimbursementRequest(ERSReimbursement ersReimbursement) {
        try(Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){

            String sql = "INSERT INTO ers_reimbursements (reimb_id, reimb_amount, reimb_submitted, reimb_description, " +
                    "reimb_author, reimb_status_id, reimb_type_id) " +
                    "VALUES (DEFAULT, ?, DEFAULT, ?, ?, DEFAULT, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setFloat(1, ersReimbursement.getReimb_amount());
            ps.setString(2, ersReimbursement.getReimb_description());
            ps.setInt(3, ersReimbursement.getReimb_author_id());
            ps.setInt(4, ersReimbursement.getReimb_type_id());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<ERSReimbursement> getAllReimbursementRequests() {
        List<ERSReimbursement> ersReimbursementList = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){

            String sql = "SELECT * FROM ers_reimbursements;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                ersReimbursementList.add(new ERSReimbursement(
                        rs.getInt(1), rs.getFloat(2), rs.getTimestamp(3),
                        rs.getTimestamp(4), rs.getString(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9)));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return ersReimbursementList;
    }

    @Override
    public List<ERSReimbursement> getAllReimbursementRequestsForUser(Integer ersUserId) {
        List<ERSReimbursement> ersReimbursementList = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){

            String sql = "SELECT * FROM ers_reimbursements WHERE reimb_author = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ersUserId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                ersReimbursementList.add(new ERSReimbursement(
                        rs.getInt(1), rs.getFloat(2), rs.getTimestamp(3),
                        rs.getTimestamp(4), rs.getString(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9)));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return ersReimbursementList;
    }

    @Override
    public void resolveAndChangeRequestStatus(ERSReimbursement ersReimbursement) {

        try(Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){

            String sql = "UPDATE ers_reimbursements SET reimb_resolved = current_timestamp, " +
                    "reimb_resolver = ?, reimb_status_id = ? WHERE reimb_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, ersReimbursement.getReimb_resolver_id());
            ps.setInt(2, ersReimbursement.getReimb_status_id());
            ps.setInt(3, ersReimbursement.getReimb_id());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public ERSReimbursement getOneReimbursementRequest(Integer reimbId) {
        ERSReimbursement ersReimbursement = null;

        try(Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){

            String sql = "SELECT * FROM ers_reimbursements WHERE reimb_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reimbId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                ersReimbursement = new ERSReimbursement(
                        rs.getInt(1),
                        rs.getFloat(2),
                        rs.getTimestamp(3),
                        rs.getTimestamp(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9)
                );
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return ersReimbursement;
    }
}
