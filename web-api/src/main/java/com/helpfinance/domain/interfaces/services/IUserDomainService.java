package com.helpfinance.domain.interfaces.services;

import com.helpfinance.domain.entities.User;
import com.helpfinance.domain.interfaces.services.base.IDomainService;

public interface IUserDomainService extends IDomainService<User> {
    User get(String email, String password);
}
