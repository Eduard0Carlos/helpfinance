package dao;

import dao.base.baseEntityDAO;
import entities.User;
import interfaces.dao.IUserDAO;

public class UserDAO extends baseEntityDAO<User> implements IUserDAO {
    public UserDAO() {
        super(User.class, "Users");
    }
}
