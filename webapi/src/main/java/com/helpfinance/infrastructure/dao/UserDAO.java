package com.helpfinance.infrastructure.dao;

import com.helpfinance.infrastructure.dao.base.baseEntityDAO;
import com.helpfinance.infrastructure.interfaces.IUserDAO;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.User;

@Repository
public class UserDAO extends baseEntityDAO<User> implements IUserDAO {
    public UserDAO() {
        super(User.class, "Users");
    }
}
