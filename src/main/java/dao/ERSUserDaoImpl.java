package dao;

import models.ERSUser;

import java.sql.*;

import static dao.DatabaseInfo.*;

public class ERSUserDaoImpl implements ERSUserDao{

    //singleton pattern
    private static ERSUserDao ersUserDao;

    private ERSUserDaoImpl(){
        //needed for tomcat
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static ERSUserDao getInstance(){
        if (ersUserDao == null){
            ersUserDao = new ERSUserDaoImpl();
        }
        return ersUserDao;
    }

    @Override
    public ERSUser getAccount(String username) {
        ERSUser ersUser = null;

        try(Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){

            String sql = "SELECT * FROM ers_users WHERE ers_username = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                ersUser = new ERSUser(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6),
                        rs.getInt(7));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return ersUser;
    }

    @Override
    public String getFirstAndLastName(Integer userId) {
        String firstAndLastName = "";

        try(Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){

            String sql = "SELECT user_first_name, user_last_name FROM ers_users WHERE ers_users_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                firstAndLastName += rs.getString(1);
                firstAndLastName += " ";
                firstAndLastName += rs.getString(2);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return firstAndLastName;
    }
}
