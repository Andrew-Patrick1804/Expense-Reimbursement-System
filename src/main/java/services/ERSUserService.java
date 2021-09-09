package services;

import models.ERSUser;

public interface ERSUserService {
    ERSUser login (ERSUser user);
    String getFirstAndLastName(Integer userId);
}
