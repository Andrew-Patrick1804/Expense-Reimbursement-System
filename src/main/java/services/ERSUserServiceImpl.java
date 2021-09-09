package services;

import dao.ERSUserDao;
import dao.ERSUserDaoImpl;
import models.ERSUser;

public class ERSUserServiceImpl implements ERSUserService {

    ERSUserDao ersUserDao;

    //singleton design pattern
    private static ERSUserService ersUserService;

    private ERSUserServiceImpl(){
        ersUserDao = ERSUserDaoImpl.getInstance();
    }

    public static ERSUserService getInstance(){
        if(ersUserService == null){
            ersUserService = new ERSUserServiceImpl();
        }
        return ersUserService;
    }

    @Override
    public ERSUser login(ERSUser user) {

        //checking if the user exists in the database
        ERSUser tempUser = ersUserDao.getAccount(user.getUsername());

        //if the user does not exist, return a null user
        if(tempUser == null){
            return null;
        }
        //if the given password doesn't match, return a null user
        else if(!tempUser.getPassword().equals(user.getPassword())){
            return null;
        }
        //if username and password match, return the user
        else{
            return tempUser;
        }
    }

    @Override
    public String getFirstAndLastName(Integer userId) {
        return ersUserDao.getFirstAndLastName(userId);
    }
}
