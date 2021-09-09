package dao;

import models.ERSUser;

public interface ERSUserDao {

    /**
     * Given a username, retrieve the corresponding record from the database's user table.
     * @param username A string representing a username.
     * @return An ERSUser corresponding to the username.  Returns null if the username is not in the database.
     */
    public ERSUser getAccount(String username);

    public String getFirstAndLastName(Integer userId);
}
