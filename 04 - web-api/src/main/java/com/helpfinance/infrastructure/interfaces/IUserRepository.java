package com.helpfinance.infrastructure.interfaces;

import com.helpfinance.domain.entities.User;
import com.helpfinance.infrastructure.interfaces.base.IRepository;

public interface IUserRepository extends IRepository<User> {
    User get(String email, String password);
}
